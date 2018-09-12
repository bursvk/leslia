package com.leslia.job.elastic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {

    public static void main(String args[]){
        new ClassPathXmlApplicationContext(new String[]{"classpath:spring/*.xml"});
    }
}
