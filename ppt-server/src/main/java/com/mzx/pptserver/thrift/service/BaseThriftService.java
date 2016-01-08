package com.mzx.pptserver.thrift.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zison on 2016/1/8.
 */
public abstract class BaseThriftService implements Runnable{
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 服务启动, 实现逻辑由具体子类去完成。
     *
     * @throws Exception
     */
    public abstract void startServer() throws Exception;

    @Override
    public void run() {
        try {
            this.startServer();
            logger.info(getClass().getSimpleName() + " start success...");
        } catch (Exception e) {
            logger.error(getClass().getSimpleName() + " start fail...");
            logger.error(e.getMessage(), e);
        }
    }
}
