package com.mzx.pptui;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zison on 2015/12/31.
 */
public class BaseTest {

    public static ClassPathXmlApplicationContext context =  null;

    public static ClassPathXmlApplicationContext getInstance() {

        if (context == null) {
            context = new ClassPathXmlApplicationContext(
                    "application-context.xml");
        }

        return context;
    }

    public Object getBean(String beanName) {
        context = getInstance();
        return context.getBean(beanName);
    }
}
