package com.mzx.pptserver.application;

import org.springframework.stereotype.Component;

import java.io.File;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ppt文件相关全局变量
 * Created by zison on 2016/1/3.
 */
@Component
public class GlobalApplication {

    private String path;
    private String fileName;
    private int curPage;
    private String key;
    private String filed;
    private int len;

    private String ip;
    private String broadcastIP;

    private ConcurrentHashMap<SocketAddress, Socket> socketPoolMap;
    private ConcurrentHashMap<SocketAddress, Socket> byteSocketPoolMap;
    private byte[] targetBytes;

    public byte[] getTargetBytes() {
        return targetBytes;
    }

    public void setTargetBytes(byte[] targetBytes) {
        this.targetBytes = targetBytes;
    }

    public ConcurrentHashMap<SocketAddress, Socket> getByteSocketPoolMap() {
        return byteSocketPoolMap;
    }

    public void setByteSocketPoolMap(ConcurrentHashMap<SocketAddress, Socket> byteSocketPoolMap) {
        this.byteSocketPoolMap = byteSocketPoolMap;
    }

    public ConcurrentHashMap<SocketAddress, Socket> getSocketPoolMap() {
        return socketPoolMap;
    }

    public void setSocketPoolMap(ConcurrentHashMap<SocketAddress, Socket> socketPoolMap) {
        this.socketPoolMap = socketPoolMap;
    }


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


    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String path) {
        this.fileName = (new File(path)).getName();
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String path) {
        this.key = (new File(path)).getName();
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }


    @Override
    public String toString() {
        return "GlobalApplication{" +
                "path='" + path + '\'' +
                ", fileName='" + fileName + '\'' +
                ", curPage=" + curPage +
                ", key='" + key + '\'' +
                ", filed='" + filed + '\'' +
                ", len=" + len +
                ", ip='" + ip + '\'' +
                ", broadcastIP='" + broadcastIP + '\'' +
                '}';
    }
}
