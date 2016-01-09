package com.mzx.pptui.thrift;

import com.mzx.pptprocotol.thrift.service.TParsePPTService;
import com.mzx.pptprocotol.thrift.struct.PPTDetail;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.nio.ByteBuffer;

/**
 * Created by zison on 2016/1/9.
 */
public class ThriftClient {


    private String ip;
    private int port;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public byte[] conParseService(String file) throws Exception{
        TTransport transport = null;
        transport = new TSocket(ip, port, 1000000);
        TProtocol protocol = new TBinaryProtocol(transport);
        TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,"parsePPTService");


        TParsePPTService.Client parseClient = new TParsePPTService.Client(mp1);

        transport.open();
        PPTDetail detail = new PPTDetail();
        detail.setPath("C:\\Users\\zison\\Desktop\\a.pptx");
        return parseClient.parsePPTAndGetFirst(detail).getBytes();
    }


    public static void main(String[] args) throws Exception{
        ThriftClient client = new ThriftClient();
        client.setIp("127.0.0.1");
        client.setPort(8090);
        byte[] bytes = client.conParseService("");
        System.out.println(bytes.length);
    }


}
