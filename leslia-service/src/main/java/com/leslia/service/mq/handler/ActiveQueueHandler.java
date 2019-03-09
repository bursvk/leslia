package com.leslia.service.mq.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ActiveQueueHandler {

    private Logger logger= LoggerFactory.getLogger(ActiveQueueHandler.class);

    public void handlers(String message){
        logger.info("接受到消息:{}",message);
    }

    public void handlerMap(Map<String,Object> map){
        logger.info("接受到消息:{}",map);
    }


}
