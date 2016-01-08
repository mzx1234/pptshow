package com.mzx.pptserver.service.impl;


import com.mzx.pptserver.exception.ServiceException;
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

    @Autowired
    private POIParse poiParse;

    @Override
    public byte[] parsePPTFile(String file) throws ServiceException {
        logger.info("parsePPTFile function is excuted");
        try {
            return poiParse.parsePPTAndGetFirst(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] parsePPTXFile(String file) throws ServiceException {
        logger.info("parsePPTXFile function is excuted");
        try {
            return poiParse.parsePPTXAndGetFirst(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
