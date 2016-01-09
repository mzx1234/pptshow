package com.mzx.pptui.utility;

import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.main.Main;
import com.mzx.pptui.thrift.clientcallable.OptionPPTThriftClient;
import com.mzx.pptui.thrift.clientcallable.ParsePPTThriftClient;

import java.awt.image.BufferedImage;


/**
 * Created by zison on 2016/1/9.
 */
public class PPTOption {


    public static byte[] load(String path) {
        GlobalApplication globalApplication = (GlobalApplication) Main.getBean("globalApplication");
        globalApplication.setPath(path);
        globalApplication.setCur(0);
        ParsePPTThriftClient parsePPTThriftClient = (ParsePPTThriftClient) Main.getBean("parsePPTThriftClient");
        PPTBytes bytes = parsePPTThriftClient.task();
//        return SerializeUtil.unserializeImg(bytes.getBytes());
        return bytes.getBytes();
    }

    public static byte[] swichPage(int cur)  {
        GlobalApplication globalApplication = (GlobalApplication) Main.getBean("globalApplication");
        globalApplication.setCur(cur);
        OptionPPTThriftClient optionPPTThriftClient = (OptionPPTThriftClient) Main.getBean("optionPPTThriftClient");
        byte[] bytes = optionPPTThriftClient.task().getBytes();
        return bytes;
    }
}
