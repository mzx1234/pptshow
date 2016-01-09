package com.mzx.pptui.application;

import org.springframework.stereotype.Component;

/**
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

    private String path;
    private int cur;


}
