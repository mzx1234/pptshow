package com.mzx.pptui.utility;

import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.main.Main;
import com.mzx.pptui.thrift.clientcallable.OptionPPTThriftClient;
import com.mzx.pptui.thrift.clientcallable.ParsePPTThriftClient;



/**
 * PPT��ز���������
 * Created by zison on 2016/1/9.
 */
public class PPTOption {


    /**
     * ���ز���
     * @param path ppt�ļ�·��
     * @return
     */
    public static byte[] load(String path) {
        GlobalApplication globalApplication = (GlobalApplication) Main.getBean("globalApplication");
        globalApplication.setPath(path);
        globalApplication.setCur(0);
        ParsePPTThriftClient parsePPTThriftClient = (ParsePPTThriftClient) Main.getBean("parsePPTThriftClient");
        PPTBytes bytes = parsePPTThriftClient.task();
        globalApplication.setLen(bytes.getPptDetail().getLen());
//        return SerializeUtil.unserializeImg(bytes.getBytes());
        return bytes.getBytes();
    }

    /**
     * �л�ҳ��
     * @param cur �л�ҳ��
     * @return
     */
    public static byte[] swichPage(int cur)  {
        GlobalApplication globalApplication = (GlobalApplication) Main.getBean("globalApplication");
        if(cur == globalApplication.getLen() || cur < 0) {
            return null;
        }
        globalApplication.setCur(cur);
        OptionPPTThriftClient optionPPTThriftClient = (OptionPPTThriftClient) Main.getBean("optionPPTThriftClient");
        byte[] bytes = optionPPTThriftClient.task().getBytes();
        return bytes;
    }
}
