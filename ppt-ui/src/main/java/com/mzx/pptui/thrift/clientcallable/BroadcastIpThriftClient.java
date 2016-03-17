package com.mzx.pptui.thrift.clientcallable;

import com.mzx.pptprocotol.thrift.service.TBroadcastIPService;
import com.mzx.pptprocotol.thrift.struct.IPDetail;
import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.bean.ThriftConnectParm;
import com.mzx.pptui.constant.TaskTypeConstant;
import com.mzx.pptui.thrift.ExecutorManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 广播ip客户端实现类
 * Created by zison on 2016/1/17.
 */
@Service
public class BroadcastIpThriftClient implements Callable {

    @Autowired
    private ThriftConnectParm thriftConnectParm;
    @Autowired
    private GlobalApplication globalApplication;
    @Autowired
    private ExecutorManager executorManager;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private void boradcastIp() {
        TTransport transport = null;
        transport = new TSocket(thriftConnectParm.getIp(), thriftConnectParm.getPort(), thriftConnectParm.getTimeOut());
        TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,thriftConnectParm.getBroadcastIPServiceName());

        TBroadcastIPService.Client broadcastIPClient = new TBroadcastIPService.Client(mp1);

        try {
            transport.open();
            IPDetail ipDetail = new IPDetail();
            ipDetail.setBroadcastIP(globalApplication.getBroadcastIP());
            ipDetail.setIp(globalApplication.getIp());
            broadcastIPClient.broadcastIP(ipDetail);
        }catch (Exception e) {
            logger.info(e.getMessage());
        }finally {
            transport.close();
        }
    }

    public void task() {
        Future<PPTBytes> future = executorManager.getThreadPoolExecutor(TaskTypeConstant.TaskType.SHARE_IP).submit(this);
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object call() throws Exception {
        boradcastIp();
        return null;
    }
}
