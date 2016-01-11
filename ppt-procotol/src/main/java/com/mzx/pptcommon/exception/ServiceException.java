package com.mzx.pptcommon.exception;

/**
 * Created by zison on 2015/12/30.
 */
public class ServiceException extends RuntimeException {

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String errorCode;
    private String msg;

}
