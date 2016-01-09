package com.mzx.pptui.thrift.clientcallable;


import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.bean.ThriftConnectParm;
import com.mzx.pptui.thrift.ExecutorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * Created by zison on 2016/1/9.
 */
 abstract class BaseThriftClient<T> implements Callable<PPTBytes> {
   protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ThriftConnectParm thriftConnectParm;
    @Autowired
    protected GlobalApplication globalApplication;

    @Autowired
    protected ExecutorManager executorManager;

    abstract PPTBytes getByte(T parm) throws Exception;

    public abstract PPTBytes task();

}
