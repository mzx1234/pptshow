package com.mzx.pptserver.aop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zison on 2016/1/10.
 */
public class BaseAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected Gson gson = new GsonBuilder().serializeNulls().create();

    public String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName();
    }

    public String getMethodName(JoinPoint joinPoint) {
        return ((MethodSignature)joinPoint.getSignature()).getMethod().getName();
    }


    public String getLogHeader(JoinPoint joinPoint) {
        return getClassName(joinPoint) + "," + getMethodName(joinPoint);
    }
}
