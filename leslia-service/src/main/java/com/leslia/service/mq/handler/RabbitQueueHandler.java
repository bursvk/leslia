package com.leslia.service.mq.handler;

import com.leslia.api.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RabbitQueueHandler {


    private Logger logger= LoggerFactory.getLogger(RabbitQueueHandler.class);

    public void handlers(String message){
        logger.info("接收到消息:{}",message);
    }

    public void handlerMap(Map<String,Object> map) {
        logger.info("接收到消息:{}",map);
    }

    public void handlerUser(User user){
        logger.info("接收到消息 userId:{},username:{}",user.getUserId(),user.getUsername());
    }



}
