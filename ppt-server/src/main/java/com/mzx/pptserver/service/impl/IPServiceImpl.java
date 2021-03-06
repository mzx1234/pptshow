package com.mzx.pptserver.service.impl;


import com.mzx.pptprocotol.thrift.struct.IPDetail;
import com.mzx.pptserver.monitor.task.BroadcastIPTask;
import com.mzx.pptserver.service.IPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IP相关服务实现类
 * Created by zison on 2015/12/31.
 */
@Service
public class IPServiceImpl implements IPService {

    @Autowired
    private BroadcastIPTask broadcastIPTask;

    @Override
    public void broadcastIP(IPDetail ipDetail) {
        broadcastIPTask.execute();
    }
}
