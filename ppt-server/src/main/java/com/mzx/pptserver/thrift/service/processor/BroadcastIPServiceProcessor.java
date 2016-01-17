package com.mzx.pptserver.thrift.service.processor;

import com.mzx.pptprocotol.thrift.service.TBroadcastIPService;

/**
 * Created by zison on 2016/1/17.
 */
public class BroadcastIPServiceProcessor extends TBroadcastIPService.Processor {
    public BroadcastIPServiceProcessor(TBroadcastIPService.Iface iface) {
        super(iface);
    }
}
