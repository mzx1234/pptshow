package com.mzx.pptui.thrift.clientcallable;

import com.mzx.pptprocotol.thrift.service.TParsePPTService;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import com.mzx.pptui.constant.TaskTypeConstant;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ppt文件解析客户端实现类
 * Created by zison on 2016/1/9.
 */
@Service
public class ParsePPTThriftClient<Stirng> extends BaseThriftClient<String>{



    public PPTBytes call() throws Exception {
        return getByte(globalApplication.getPath());
    }

    PPTBytes getByte(String parm) {
        TTransport transport = null;
        transport = new TSocket(thriftConnectParm.getIp(), thriftConnectParm.getPort(), thriftConnectParm.getTimeOut());
        TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,thriftConnectParm.getParsePPTServiceName());

        TParsePPTService.Client parseClient = new TParsePPTService.Client(mp1);

        try {
            transport.open();
            PPTDetail detail = new PPTDetail();
            detail.setPath(parm);
            detail.setCurPage(globalApplication.getCur());
            detail.setLen(globalApplication.getLen());
            return parseClient.parsePPTAndGetFirst(detail);
        }catch (Exception e) {
            logger.info(e.getMessage());
        }finally {
            transport.close();
        }
        return null;
    }

    @Override
    public PPTBytes task() {
        Future<PPTBytes> future = executorManager.getThreadPoolExecutor(TaskTypeConstant.TaskType.PPT_OPTION).submit(this);
        try {
//            return future.get();
            PPTBytes bytes = future.get();
            if(bytes == null) {
                logger.error("返回值为null");
                return bytes;
            }
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
