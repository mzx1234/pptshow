package com.mzx.pptserver.thrift.service;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Thrift接口服务启动类
 * Created by zison on 2016/1/7.
 */
public class ThriftServiceStartUp extends BaseThriftService  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String ip;

    private int port;

    /**
     * 存储thrift接口服务对象
     */
    private Map<String, TProcessor> serverMap;


    public Map<String, TProcessor> getServerMap() {
        return serverMap;
    }

    public void setServerMap(Map<String, TProcessor> serverMap) {
        this.serverMap = serverMap;
    }

    public String getIp() {
        return ip;

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    @Override
    public void startServer() throws Exception {
        TMultiplexedProcessor multiplexedProcessor = new TMultiplexedProcessor();
        InetSocketAddress socketAddress = new InetSocketAddress(this.getIp(), this.getPort());
        TServerTransport serverTransport = new TServerSocket(socketAddress);
        TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(multiplexedProcessor));
        for (Map.Entry<String, TProcessor> entry : serverMap.entrySet()) {
            multiplexedProcessor.registerProcessor(entry.getKey(), entry.getValue());
            logger.info(entry.getKey() + " register " + entry.getKey() + " successed ...");
        }
        server.serve();
        logger.info("shut");
    }



}
