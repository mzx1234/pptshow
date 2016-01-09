package com.mzx.pptui.bean;

/**
 * Created by zison on 2016/1/9.
 */
public class ThriftConnectParm {
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getOptionPPTServiceName() {
        return optionPPTServiceName;
    }

    public void setOptionPPTServiceName(String optionPPTServiceName) {
        this.optionPPTServiceName = optionPPTServiceName;
    }

    public String getParsePPTServiceName() {
        return parsePPTServiceName;
    }

    public void setParsePPTServiceName(String parsePPTServiceName) {
        this.parsePPTServiceName = parsePPTServiceName;
    }


    private String ip;
    private int port;
    private int timeOut;
    private String parsePPTServiceName;
    private String optionPPTServiceName;


}
