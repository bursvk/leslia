package com.leslia.inter.consumer;

import com.leslia.inter.api.InitDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InitDemoConsumer {

    private static Logger logger= LoggerFactory.getLogger(InitDemoConsumer.class);

    public static ClassPathXmlApplicationContext context;

    static {
        logger.info("--加载配置文件--");
        context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
    }


    public static void main(String args[]) throws Exception{
        logger.info("main 方法");
        InitDemoService initDemoService=(InitDemoService)context.getBean("initDemoService");
        String s=initDemoService.helloWorld("苏里");
        logger.info(s);
        System.in.read();
    }

}
