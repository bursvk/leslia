package com.leslia.resta.jcrontab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JCronTabTask implements Runnable {

    private static Logger logger= LoggerFactory.getLogger(JCronTabTask.class);

    public JCronTabTask(String args[]){
        System.out.println(args);
        for(int i=0;i<args.length;i++){
            logger.info("参数:{}",args[i]);
        }
    }

    @Override
    public void run() {
        logger.info("JCronTab : hello world");
    }

    public static void main(String args[]){
        logger.info("JCronTab : main...");
    }

}
