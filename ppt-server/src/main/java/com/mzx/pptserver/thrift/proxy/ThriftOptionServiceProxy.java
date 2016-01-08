package com.mzx.pptserver.thrift.proxy;



import com.mzx.pptprocotol.thrift.service.TOptionService;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import com.mzx.pptserver.service.OptionService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by zison on 2016/1/7.
 */
@Service
public class ThriftOptionServiceProxy implements TOptionService.Iface {

    @Autowired
    private OptionService optionService;

    @Override
    public PPTBytes swichPPTPage(PPTDetail parm) throws TException {
        int cur = parm.getCurPage();
        byte[] bytes = optionService.swichPPTPage(cur);
        PPTBytes pptBytes = new PPTBytes();
        pptBytes.setBytes(bytes);
        pptBytes.setPptDetail(parm);
        return pptBytes;
    }
}
