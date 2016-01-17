package com.mzx.pptui.utility;

import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.main.Main;
import com.mzx.pptui.thrift.clientcallable.BroadcastIpThriftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 广播ip工具类
 * Created by zison on 2016/1/17.
 */
public class NetWorkOption {

    private static Logger logger = LoggerFactory.getLogger(NetWorkOption.class);
    private static String sub= "255.255.255.0";

    public static void broadcastIP(String ip) {
        GlobalApplication globalApplication = (GlobalApplication) Main.getBean("globalApplication");

        String subnetString = sub;

        NetWorkInfo netWorkInfo = new NetWorkInfo(ip,
                subnetString);
        String broadcastAddress = netWorkInfo.getBroadcastAddress();
        logger.info("广播IP为:" + broadcastAddress);

        //设置IP和广播IP全局变量
        globalApplication.setIp(ip);
        globalApplication.setBroadcastIP(broadcastAddress);

        BroadcastIpThriftClient client = (BroadcastIpThriftClient)
                Main.getBean("broadcastIpThriftClient");
        client.task();
    }
}
