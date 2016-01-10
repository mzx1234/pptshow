package com.mzx.pptserver.thrift.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * thrift接口启动工厂
 * Created by zison on 2016/1/8.
 */
@Service
public class ThriftServiceStartUpFactory implements InitializingBean{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ThriftServiceStartUp thriftServiceStartUp;
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            logger.info(thriftServiceStartUp.getClass().getSimpleName()+ ": starting ...");
            (new Thread(thriftServiceStartUp)).start();
            logger.info(thriftServiceStartUp.getClass().getSimpleName() + ": successed ...");
        } catch (Exception e) {
            logger.error(thriftServiceStartUp.getClass().getSimpleName() + ": start fail ...");
            logger.error(e.getMessage(), e);
        }

    }
}
