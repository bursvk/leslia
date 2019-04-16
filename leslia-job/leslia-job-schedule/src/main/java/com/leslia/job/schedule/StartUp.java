package com.leslia.job.schedule;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {

    public static void main(String args[]){
        new ClassPathXmlApplicationContext(new String[]{"classpath:spring/*.xml"});
    }

}
