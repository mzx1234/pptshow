package com.mzx.pptserver.aop;

import com.mzx.pptcommon.constant.SystemConstant;
import com.mzx.pptcommon.exception.PPTshowException;
import com.mzx.pptprocotol.thrift.struct.ResponseStatus;
import org.apache.thrift.TException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Created by zison on 2016/1/11.
 */
@Component
@Aspect
@Order(3)
public class ExceptionAspect extends BaseAspect{

    @Around("execution(* com.mzx.pptserver.thrift.proxy..*.*(..))")
    public Object processAround(ProceedingJoinPoint jp) throws TException {
        logger.info("进入异常切面");
        try {
            return jp.proceed(jp.getArgs());
        } catch (Throwable throwable) {
            printLogMethodException(jp, throwable);
            throwable.printStackTrace();
            return convertExceptionToThriftResult(jp, throwable);
        }
    }


    /**
     * 把异常信息覆盖到返回的结构体中
     * @param jp
     * @param throwable
     * @return
     */
    private Object convertExceptionToThriftResult(ProceedingJoinPoint jp, Throwable throwable) {
        Class returnType = ((MethodSignature)jp.getSignature()).getReturnType();
        Object returnValue = null;
        try {
            returnValue = returnType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //默认系统异常
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setCode(SystemConstant.ResponseStatusCode.ABNORMAL.getCode());
        responseStatus.setMsg(SystemConstant.ResponseStatusCode.ABNORMAL.getDescription());
        if(throwable instanceof PPTshowException) {
            responseStatus.setCode(((PPTshowException)throwable).getErrorCode());
            responseStatus.setMsg(((PPTshowException)throwable).getMsg());
        }

        logger.info(responseStatus.toString());

        //将转好之后的ResponseStatus赋值给返回值。
        try {
            Field responseStatusField = returnType.getDeclaredField("responseStatus");
            responseStatusField.set(returnValue,responseStatus);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            //do nothing
        }
        return returnValue;
    }



    /**
     * 打印方法异常日志。
     *
     * @param jp 切入点
     * @param e  异常
     */
    protected void printLogMethodException(JoinPoint jp, Throwable e) {
        logger.error("-----------------printLogMethodException start--------------------------");
        logger.error(getLogHeader(jp) + " exception## arguments: " + gson.toJson(jp.getArgs()));
        logger.error(getLogHeader(jp) + " exception## " + e.getMessage(), e);
        logger.error("-----------------printLogMethodException end---------------------------");
    }

}
