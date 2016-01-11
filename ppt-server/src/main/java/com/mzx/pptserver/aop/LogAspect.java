package com.mzx.pptserver.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created by zison on 2016/1/10.
 */
@Component
@Aspect
@Order(1)
public class LogAspect extends BaseAspect{


    @Around("execution(* com.mzx.pptserver.thrift.proxy..*.*(..))")
    public Object processMethodLog(ProceedingJoinPoint joinPoint) {
        String argsJson = gson.toJson(joinPoint.getArgs());
        logger.info(String.format("%s Start## arguments: %s", getLogHeader(joinPoint), argsJson));
        Object result = null;
        // 方法开始时间
        long start = System.currentTimeMillis();
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //方法结束时间
        long over = System.currentTimeMillis();
        logger.info(String.format("%s Over## Finished after: %sms. Args: %s",
                getLogHeader(joinPoint), (over - start), argsJson));

        return result;
    }

}
