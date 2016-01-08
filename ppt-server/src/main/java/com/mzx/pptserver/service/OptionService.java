package com.mzx.pptserver.service;


/**
 *
 * 响应操作服务
 * Created by zison on 2015/12/30.
 */
public interface OptionService {

    /**
     * ppt页面切换
     * @param cur
     */
    public byte[] swichPPTPage(int cur);
}
