package com.leslia.test.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorTest {

    private static Logger logger= LoggerFactory.getLogger(ScheduledExecutorTest.class);

    //执行
    public static void b(){
        ScheduledExecutorService service=Executors.newScheduledThreadPool(10);
        service.schedule(()->{System.out.println("b");},1,TimeUnit.SECONDS);
        service.shutdown();
    }

    //循环执行
    public static void bb(){
        ScheduledExecutorService service=Executors.newScheduledThreadPool(10);
        //每隔3秒
        service.scheduleAtFixedRate(()->{delay();logger.info("bbb");},1,3,TimeUnit.SECONDS);
        //任务结束后隔3秒
        service.scheduleWithFixedDelay(()->{delay();logger.info("bbb");},1,3,TimeUnit.SECONDS);
    }

    public static void main(String args[]){
        bb();
    }


    public static void delay(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
