package com.mzx.pptserver.monitor;

import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.monitor.task.SendByteTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zison on 2016/1/17.
 */
@Service
public class SendByteManager implements InitializingBean {

    /**
     * 日志对象。
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalApplication globalApplication;

    @Autowired
    private SendByteTask sendByteTask;


    @Override
    public void afterPropertiesSet() throws Exception {
        globalApplication.setByteSocketPoolMap(new ConcurrentHashMap<SocketAddress, Socket>());
        // 发送文件线程
        new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                SendPPT();
            }
        }.start();
    }


    private void SendPPT() {
        try {
            ServerSocket loadServer = new ServerSocket(8889);
            while (true) {
                Socket loadSocket = loadServer.accept();
                logger.info("有客户端连上");
                globalApplication.getByteSocketPoolMap().put(loadSocket.getRemoteSocketAddress(), loadSocket);

//                SendByteTask sendByteTask = new SendByteTask();
//                sendByteTask.setSocket(loadSocket);
                sendByteTask.execute();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
