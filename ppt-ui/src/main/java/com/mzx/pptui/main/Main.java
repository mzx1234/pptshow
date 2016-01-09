package com.mzx.pptui.main;


import com.mzx.pptui.ui.AndroidPPT;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zison on 2016/1/6.
 */
public class Main {

    public static ClassPathXmlApplicationContext context =  null;

    public static void main(String []args) {
        context = new ClassPathXmlApplicationContext(
                "application-context.xml");

        new AndroidPPT("SharePPT¿œ ¶∂À");
    }


    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
