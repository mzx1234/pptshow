package com.mzx.pptserver.utility;



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
import java.util.List;

/**
 * POI解析工具类
 * Created by zison on 2015/12/31.
 */
public class POIParse {

    private Logger logger = LoggerFactory.getLogger(getClass());

    List<HSLFSlide> pptSlideList;
    List<XSLFSlide> pptxSlideList;
    private Dimension pgsize = null;
    private int len;


    private PPTType pptType ;

    @Autowired
    private GlobalApplication globalApplication;
    @Autowired
    private RedisUtil redisUtil;



    private  void getPPTSlides(String file) throws Exception{
        logger.info("getPPTSlides function start");
        HSLFSlideShow pptSlideShow = new HSLFSlideShow(new HSLFSlideShowImpl(file));
        pgsize = pptSlideShow.getPageSize();
        pptSlideList = pptSlideShow.getSlides();
        pptType = PPTType.PPT;
        len = pptSlideList.size();


        logger.info("getPPTSlides function is finish");
    }


    private void getPPTXSlides(String file) throws Exception{
        logger.info("getPPTXSlides function start");
        XMLSlideShow pptxSlideShow = new XMLSlideShow(new FileInputStream(file));
        pgsize = pptxSlideShow.getPageSize();
        pptxSlideList = pptxSlideShow.getSlides();
        pptType = PPTType.PPTX;
        len = pptxSlideList.size();

        logger.info("getPPTXSlides function is finish");
    }

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
        }
        logger.info("getImag function finish");
        return img;
    }


    private void toRedis() {
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

    private byte[] getFromRedis(String file) {
        innitGlobal(file);
        return redisUtil.hGetBytes(globalApplication.getKey(), "0");
    }


    public byte[] parsePPTAndGetFirst(String file) throws Exception{
        byte[] result = getFromRedis(file);
        if(result != null) {
            return result;
        }
        getPPTSlides(file);
        globalApplication.setLen(len);
        toRedis();
        return SerializeUtil.serializeImg(getImag(0));
    }

    public byte[] parsePPTXAndGetFirst(String file) throws Exception{
        byte[] result = getFromRedis(file);
        if(result != null) {
            return result;
        }
        getPPTXSlides(file);
        innitGlobal(file);
        toRedis();
        return SerializeUtil.serializeImg(getImag(0));
    }


    private void innitGlobal(String file) {
        globalApplication.setPath(file);
        globalApplication.setFileName(file);
        globalApplication.setCurPage(0);
        globalApplication.setKey(globalApplication.getFileName());
        globalApplication.setLen(len);
    }




}
