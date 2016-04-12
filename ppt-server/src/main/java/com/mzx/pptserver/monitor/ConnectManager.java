package com.mzx.pptserver.monitor;

import com.mzx.pptserver.application.GlobalApplication;
import com.mzx.pptserver.monitor.task.SendMsgTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zison on 2016/1/17.
 */
@Service
public class ConnectManager implements InitializingBean{

    /**
     * 日志对象。
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalApplication globalApplication;

    @Autowired
    private SendMsgTask sendMsgTask;



    @Override
    public void afterPropertiesSet() throws Exception {
        globalApplication.setSocketPoolMap(new ConcurrentHashMap<SocketAddress, Socket>());

        // 同步线程
        new Thread() {
            public void run() {
                try {
                    connect();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 同步连接
     *
     * @throws Exception
     */
    private void connect() throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("同步监听");
        while (true) {
            final Socket socket = server.accept();
            logger.info("有客户端连上");
            globalApplication.getSocketPoolMap().put(socket.getRemoteSocketAddress(), socket);
//            final SendMsgTask sendMsgTask = new SendMsgTask();
            new Thread() {
                @Override
                public void run() {
                    sendMsgTask.sendMessage(socket);
                }
            }.start();
        }
    }
}
