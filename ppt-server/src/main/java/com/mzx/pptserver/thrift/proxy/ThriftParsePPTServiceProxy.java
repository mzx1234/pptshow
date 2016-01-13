package com.mzx.pptserver.thrift.proxy;


import com.mzx.pptcommon.constant.SystemConstant;
import com.mzx.pptcommon.exception.PPTshowException;
import com.mzx.pptprocotol.thrift.service.TParsePPTService;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import com.mzx.pptprocotol.thrift.struct.ResponseStatus;
import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.constant.PptTypeConstant.PPTType;
import com.mzx.pptserver.service.ParseService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


/**
 * 解析ppt文件服务Thrift接口实现类
 * Created by zison on 2016/1/7.
 */
@Service
public class ThriftParsePPTServiceProxy implements TParsePPTService.Iface{

    @Autowired
    private ParseService parseService;
    @Autowired
    private GlobalApplication globalApplication;
    @Override
    public PPTBytes parsePPTAndGetFirst(PPTDetail parm) throws TException {
        String path = parm.path;
        byte[] bytes;

        switch (isPPT(path)) {
            case PPT:
                bytes = parseService.parsePPTFile(path);
                break;
            case PPTX:
                bytes = parseService.parsePPTXFile(path);
                break;
            default:
                bytes = null;
                throw new PPTshowException(SystemConstant.ResponseStatusCode.ABNORMAL.getCode(),
                        "你选择的不是ppt文件");
        }

        PPTBytes pptBytes = new PPTBytes();
        PPTDetail pptDetail = new PPTDetail();
        pptDetail.path = globalApplication.getPath();
        pptDetail.fileName = globalApplication.getFileName();
        pptDetail.curPage = globalApplication.getCurPage();
        pptDetail.len = globalApplication.getLen();
        pptBytes.pptDetail = pptDetail;
        pptBytes.setBytes(bytes);

        ResponseStatus responseStatus = new ResponseStatus();
        pptBytes.setResponseStatus(responseStatus);

        return pptBytes;
    }

    private PPTType isPPT(String path) {
        String fileName = (new File(path)).getName();
        if(fileName.endsWith("ppt"))
            return PPTType.PPT;
        else if(fileName.endsWith("pptx"))
            return PPTType.PPTX;
        else
            return PPTType.NOTPPT;
    }
}
