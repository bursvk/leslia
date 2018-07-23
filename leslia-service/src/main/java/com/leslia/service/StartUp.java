package com.leslia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {

    private static Logger logger= LoggerFactory.getLogger(StartUp.class);

    public static void main(String args[]){
        new ClassPathXmlApplicationContext(new String[]{"classpath*:spring/*.xml"});
      /*  try{
            ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"classpath*:spring/*.xml"});
            context.start();
            synchronized (StartUp.class){
                while(true){
                    try{
                        StartUp.class.wait();
                        logger.info("success! dubbo service in service...");
                    }catch (InterruptedException e){
                        logger.error("== synchronized error:",e);
                    }
                }
            }
        }catch (Exception e){
            logger.error("== dubbo provider context start error:",e);
        }*/
    }
}
