package com.mzx.pptui.thrift;

import com.mzx.pptprocotol.thrift.struct.PPTBytes;
import com.mzx.pptui.BaseTest;
import com.mzx.pptui.application.GlobalApplication;
import com.mzx.pptui.thrift.clientcallable.ParsePPTThriftClient;
import org.testng.annotations.Test;

/**
 * Created by zison on 2016/1/9.
 */

public class ParsePPTThriftClientTest extends BaseTest{

    @Test
    public void testPaserPPTThriftClient() throws Exception {
        ParsePPTThriftClient client = (ParsePPTThriftClient) getBean("parsePPTThriftClient");
        GlobalApplication application = (GlobalApplication) getBean("globalApplication");
        application.setPath("C:\\Users\\zison\\Desktop\\a.pptx");
        application.setCur(0);
        PPTBytes bytes = client.task();
        System.out.println(bytes.getBytes().length);
    }
}
