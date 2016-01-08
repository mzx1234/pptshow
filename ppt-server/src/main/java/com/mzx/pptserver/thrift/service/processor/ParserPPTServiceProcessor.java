package com.mzx.pptserver.thrift.service.processor;


import com.mzx.pptprocotol.thrift.service.TParsePPTService;

/**
 * Created by zison on 2016/1/7.
 */

public class ParserPPTServiceProcessor  extends TParsePPTService.Processor{

    public ParserPPTServiceProcessor(TParsePPTService.Iface iface) {
        super(iface);
    }

}
