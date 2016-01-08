package com.mzx.pptserver.utility;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by zison on 2016/1/2.
 */
public class BrocastObjectUtil {

    public static void brocastObject(Object obj, String brocastAdress) throws Exception {
        DatagramSocket dgSocket = new DatagramSocket(8989);
        byte[] by = SerializeUtil.serialize(obj);
        DatagramPacket packet = new DatagramPacket(by,
                by.length, InetAddress
                .getByName(brocastAdress),
                8989);
        dgSocket.send(packet);
        dgSocket.close();
    }
}
