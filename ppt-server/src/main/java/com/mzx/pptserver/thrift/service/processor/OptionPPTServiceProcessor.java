package com.mzx.pptserver.thrift.service.processor;


import com.mzx.pptprocotol.thrift.service.TOptionService;

/**
 * Created by zison on 2016/1/7.
 */

public class OptionPPTServiceProcessor extends TOptionService.Processor {
    public OptionPPTServiceProcessor(TOptionService.Iface iface) {
        super(iface);
    }
}
