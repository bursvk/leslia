package com.leslia.consumer.inter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InitDemoConsumer {

    private static Logger logger= LoggerFactory.getLogger(InitDemoConsumer.class);

    public static ClassPathXmlApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
    }


    public static void main(String args[]){
        try{
         /*   InitDemoService initDemoService=(InitDemoService)context.getBean("initDemoService");
            String s=initDemoService.helloWorld("苏里");*/
            logger.info("111111111111111");
        }catch (Exception e){
            logger.error("dubbo consumer fail...");
        }
        synchronized (InitDemoConsumer.class){
            while(true){
                try{
                    InitDemoConsumer.class.wait();
                }catch (InterruptedException e){
                    logger.error("== synchronized error:",e);
                }
            }
        }
    }


}
