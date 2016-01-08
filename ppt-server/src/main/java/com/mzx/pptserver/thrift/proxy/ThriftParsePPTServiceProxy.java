package com.mzx.pptserver.thrift.proxy;


import com.mzx.pptprocotol.thrift.service.TParsePPTService;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.service.ParseService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zison on 2016/1/7.
 */
@Service
public class ThriftParsePPTServiceProxy implements TParsePPTService.Iface{

    @Autowired
    private ParseService parseService;
    private GlobalApplication globalApplication;
    @Override
    public PPTBytes parsePPTAndGetFirst(PPTDetail parm) throws TException {
        String path = parm.path;
        byte[] bytes = parseService.parsePPTFile(path);
        PPTBytes pptBytes = new PPTBytes();
        PPTDetail pptDetail = new PPTDetail();
        pptDetail.path = globalApplication.getPath();
        pptDetail.fileName = globalApplication.getFileName();
        pptDetail.curPage = globalApplication.getCurPage();
        pptBytes.pptDetail = pptDetail;
        pptBytes.setBytes(bytes);

        return pptBytes;
    }
}
