package com.mzx.pptserver.utility;



import com.mzx.pptcommon.constant.SystemConstant;
import com.mzx.pptcommon.exception.PPTshowException;
import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.constant.PptTypeConstant.PPTType;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * POI解析ppt文件工具类
 * Created by zison on 2015/12/31.
 */
public class POIParse {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<HSLFSlide> pptSlideList;
    List<XSLFSlide> pptxSlideList;
    /**
     * ppt页面大小
     */
    private Dimension pgsize = null;
    /**
     * ppt文件页数
     */
    private int len;

    /**
     * ppt文件类型，包括ppt和pptx
     */
    private PPTType pptType ;

    @Autowired
    private GlobalApplication globalApplication;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 通过文件路径解析ppt文件成Slide类型
     * @param file
     * @throws Exception
     */
    private  void getPPTSlides(String file) {
        logger.info("getPPTSlides function start");
        HSLFSlideShow pptSlideShow = null;
        try {
            pptSlideShow = new HSLFSlideShow(new HSLFSlideShowImpl(file));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件不存在或者文件不是.ppt文件");
            throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),
                    "文件不存在或者文件不是ppt文件");
        }
        pgsize = pptSlideShow.getPageSize();
        pptSlideList = pptSlideShow.getSlides();
        pptType = PPTType.PPT;
        len = pptSlideList.size();


        logger.info("getPPTSlides function is finish");
    }


    /**
     * 通过文件路径解析pptx文件成Slide类型
     * @param file
     * @throws Exception
     */
    private void getPPTXSlides(String file) {
        logger.info("getPPTXSlides function start");
        XMLSlideShow pptxSlideShow = null;
        try {
            pptxSlideShow = new XMLSlideShow(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件不存在或者文件不是pptx文件");
            throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),
                    "文件不存在或者文件不是.pptx文件");
        }
        pgsize = pptxSlideShow.getPageSize();
        pptxSlideList = pptxSlideShow.getSlides();
        pptType = PPTType.PPTX;
        len = pptxSlideList.size();

        logger.info("getPPTXSlides function is finish");
    }

    /**
     * 将对应的Slide转化成BufferedImage
     * @param cur 需要转化的页码
     * @return
     */
    private BufferedImage getImag(int cur) {
        logger.info("getImag function start");
        BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D jpgGraphics = img.createGraphics();
        // clear the drawing area

        jpgGraphics.setPaint(Color.white);
        jpgGraphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

        switch (pptType) {
            case PPT:
                pptSlideList.get(cur).draw(jpgGraphics);
                break;
            case PPTX:
                pptxSlideList.get(cur).draw(jpgGraphics);
                break;
            default:
                logger.error("你选择的不是ppt文件");
                throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),
                        "你选择的不是ppt文件");
        }
        logger.info("getImag function finish");
        return img;
    }


    /**
     * 将解析得到的img先转化为byte数组，然后缓存到redis中散列表类型
     * key 为文件名fileName
     * filed 为ppt对应页码
     */
    private void toRedis() throws PPTshowException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("toRedis function start");
                for(int cur = 0; cur < globalApplication.getLen(); cur++) {
                    BufferedImage img = getImag(cur);
                    redisUtil.hSetObject(globalApplication.getKey(), cur+"", img);
                }
                logger.info("toRedis function finish");
            }
        }).start();

    }

    /**
     * 通过文件名（key）从缓存redis中获取filed为“0”的value
     * @param file 文件名
     * @return
     */
    private byte[] getFromRedis(String file) throws PPTshowException {
        innitGlobal(file);
        return redisUtil.hGetBytes(globalApplication.getKey(), "0");
    }


    /**
     * 供服务接口调用的函数，解析ppt文件并返回首页数据
     * @param file ppt文件路径
     * @return
     * @throws Exception
     */
    public byte[] parsePPTAndGetFirst(String file) throws PPTshowException {
        byte[] result = getFromRedis(file);
        /**如果返回值不为null，则证明缓存中存在，直接返回，否则重新解析*/
        if(result != null) {
            globalApplication.setLen((redisUtil.hGetLen(globalApplication.getKey())).intValue());
            return result;
        }
        getPPTSlides(file);
        globalApplication.setLen(len);
        toRedis();
        return SerializeUtil.serializeImg(getImag(0));
    }

    /**
     * 供服务接口调用的函数，解析pptx文件并返回首页数据
     * @param file pptx文件路径
     * @return
     * @throws Exception
     */
    public byte[] parsePPTXAndGetFirst(String file) throws PPTshowException {
        byte[] result = getFromRedis(file);
        if(result != null) {
            globalApplication.setLen((redisUtil.hGetLen(globalApplication.getKey())).intValue());
            return result;
        }
        getPPTXSlides(file);
        innitGlobal(file);
        toRedis();
        return SerializeUtil.serializeImg(getImag(0));
    }


    /**
     * 初始化全局变量对象
     * @param file
     */
    private void innitGlobal(String file) {
        globalApplication.setPath(file);
        globalApplication.setFileName(file);
        globalApplication.setCurPage(0);
        globalApplication.setKey(file);
        globalApplication.setLen(len);
    }




}
