package com.mzx.pptserver.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主函数
 * Created by zison on 2016/1/8.
 */
public class Main {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext(
                "application-context.xml");
        System.out.println("======================");
        System.out.println("ppt service start...");
        System.out.println("======================");
    }
}
