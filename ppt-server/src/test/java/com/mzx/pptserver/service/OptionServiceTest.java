package com.mzx.pptserver.service;


import com.mzx.pptserver.BaseTest;
import com.mzx.pptserver.service.impl.OptionServiceImpl;
import org.testng.annotations.Test;

/**
 * Created by zison on 2016/1/3.
 */
public class OptionServiceTest extends BaseTest {

    @Test
    public void testParseService() throws Exception{
        OptionServiceImpl optionService = (OptionServiceImpl) getBean("optionServiceImpl");
//        parseService.parsePPTFile("C:\\Users\\zison\\Desktop\\java.ppt");
        optionService.swichPPTPage(2);
        Thread.sleep(10 * 1000);
    }
}
