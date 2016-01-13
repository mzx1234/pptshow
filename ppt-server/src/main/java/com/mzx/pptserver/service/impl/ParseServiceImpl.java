package com.mzx.pptserver.service.impl;


import com.mzx.pptcommon.exception.PPTshowException;
import com.mzx.pptserver.service.ParseService;
import com.mzx.pptserver.utility.POIParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 解析PPT文件服务实现类
 * Created by zison on 2015/12/30.
 */
@Service
public class ParseServiceImpl implements ParseService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * ppt文件解析实现类
     */
    @Autowired
    private POIParse poiParse;

    @Override
    public byte[] parsePPTFile(String file) throws PPTshowException {
        logger.info("parsePPTFile function is excuted");
        return poiParse.parsePPTAndGetFirst(file);
    }

    @Override
    public byte[] parsePPTXFile(String file) throws PPTshowException {
        logger.info("parsePPTXFile function is excuted");
        return poiParse.parsePPTXAndGetFirst(file);
    }
}


