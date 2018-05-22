package com.leslia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartUp {

    private static Logger logger= LoggerFactory.getLogger(StartUp.class);

    public static void main(String args[]){
        try{
            ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"dubbo-provider.xml"});
            context.start();
        }catch (Exception e){
            logger.info("== dubbo provider context start error:",e);
        }
        synchronized (StartUp.class){
            while(true){
                try{
                    StartUp.class.wait();
                }catch (InterruptedException e){
                    logger.error("== synchronized error:",e);
                }
            }
        }
    }
}
