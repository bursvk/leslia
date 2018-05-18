package com.leslia.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {

    public static void main(String args[]) throws  Exception{
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"dubbo-provider.xml"});
        context.start();
        System.out.print("服务已启动.....");
        System.in.read();
    }
}
