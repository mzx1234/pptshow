package com.mzx.pptui.thrift.clientcallable;

import com.mzx.pptcommon.constant.SystemConstant;
import com.mzx.pptprocotol.thrift.service.TOptionService;
import com.mzx.pptprocotol.thrift.service.TParsePPTService;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.constant.TaskTypeConstant.TaskType;
import com.mzx.pptui.main.Main;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ppt相关操作客户端实现类
 * Created by zison on 2016/1/9.
 */
@Service
public class OptionPPTThriftClient extends BaseThriftClient<Integer>{

    @Override
    PPTBytes getByte(Integer parm)  {

        TTransport transport = null;
        transport = new TSocket(thriftConnectParm.getIp(), thriftConnectParm.getPort(), thriftConnectParm.getTimeOut());
        TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,thriftConnectParm.getOptionPPTServiceName());

        TOptionService.Client optionClient = new TOptionService.Client(mp1);

        try {
            transport.open();
            PPTDetail detail = new PPTDetail();
            detail.setCurPage(parm);
            detail.setPath(globalApplication.getPath());
            detail.setLen(globalApplication.getLen());
            return optionClient.swichPPTPage(detail);
        }catch (Exception e) {
            logger.info(e.getMessage());
        }finally {
            transport.close();
        }
        return null;
    }

    public PPTBytes call() throws Exception {
        return getByte(globalApplication.getCur());
    }

    @Override
    public PPTBytes task() {
        Future<PPTBytes> future = executorManager.getThreadPoolExecutor(TaskType.PPT_OPTION).submit(this);
        try {
            PPTBytes bytes = future.get();
            if(bytes.getResponseStatus().getCode().equals(
                    SystemConstant.ResponseStatusCode.NORMAL.getCode())) {
                logger.info(bytes.getBytes().length + "");
                return bytes;
            }
            else {
                logger.error(bytes.getResponseStatus().getMsg());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
