package com.mzx.pptui.application;

import org.springframework.stereotype.Component;

/**
 * ppt文件相关全局变量
 * Created by zison on 2016/1/9.
 */
@Component
public class GlobalApplication {

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }


    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }


    private String path;
    private int cur;

    private int len;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBroadcastIP() {
        return broadcastIP;
    }

    public void setBroadcastIP(String broadcastIP) {
        this.broadcastIP = broadcastIP;
    }

    private String ip;

    private String broadcastIP;

    @Override
    public String toString() {
        return "GlobalApplication{" +
                "path='" + path + '\'' +
                ", cur=" + cur +
                ", len=" + len +
                '}';
    }
}
