package com.mzx.pptserver.application;

import org.springframework.stereotype.Component;

import java.io.File;

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
                '}';
    }
}
