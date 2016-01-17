package com.mzx.pptserver.service;

import com.mzx.pptprocotol.thrift.struct.IPDetail;

/**
 * IP相关服务
 * Created by zison on 2015/12/30.
 */
public interface IPService {

    /**
     * 广播IP服务
     */
    void broadcastIP(IPDetail ipDetail);
}
