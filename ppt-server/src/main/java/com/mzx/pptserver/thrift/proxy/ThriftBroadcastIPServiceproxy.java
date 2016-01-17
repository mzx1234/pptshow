package com.mzx.pptserver.thrift.proxy;

import com.mzx.pptprocotol.thrift.service.TBroadcastIPService;
import com.mzx.pptprocotol.thrift.struct.IPDetail;
import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.service.IPService;
import com.mzx.pptserver.service.ParseService;
import com.mzx.pptserver.service.impl.IPServiceImpl;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zison on 2016/1/17.
 */
@Service
public class ThriftBroadcastIPServiceproxy implements TBroadcastIPService.Iface {

    @Autowired
    private IPService ipService;
    @Autowired
    private GlobalApplication globalApplication;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void broadcastIP(IPDetail ipDetail) throws TException {
        logger.info(ipDetail.toString());
        ipService.broadcastIP(ipDetail);
    }
}
