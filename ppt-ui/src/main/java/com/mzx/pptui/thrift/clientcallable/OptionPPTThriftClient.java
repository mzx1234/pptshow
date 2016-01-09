package com.mzx.pptui.thrift.clientcallable;

import com.mzx.pptprocotol.thrift.service.TOptionService;
import com.mzx.pptprocotol.thrift.service.TParsePPTService;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import com.mzx.pptui.constant.TaskTypeConstant.TaskType;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by zison on 2016/1/9.
 */
@Service
public class OptionPPTThriftClient extends BaseThriftClient<Integer>{
    @Override
    PPTBytes getByte(Integer parm) throws Exception {
        TTransport transport = null;
        transport = new TSocket(thriftConnectParm.getIp(), thriftConnectParm.getPort(), thriftConnectParm.getTimeOut());
        TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,thriftConnectParm.getOptionPPTServiceName());

        TOptionService.Client optionClient = new TOptionService.Client(mp1);

        try {
            transport.open();
            PPTDetail detail = new PPTDetail();
            detail.setCurPage(parm);
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
            logger.info(bytes.getBytes().length+"");
            return bytes;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
