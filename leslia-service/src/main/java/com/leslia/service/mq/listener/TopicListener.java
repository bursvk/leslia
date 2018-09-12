package com.leslia.service.mq.listener;

import com.leslia.ware.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;



public class TopicListener implements MessageListener {

    private Logger logger= LoggerFactory.getLogger(TopicListener.class);

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public void onMessage(Message message) {

        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if(message instanceof TextMessage){
                        TextMessage textMessage = (TextMessage) message;
                        logger.info("主题名称：{}  监听消息：{}","service.topic",textMessage.getText());
                        redisUtil.hset("topic","service.topic",textMessage.getText());
                    }
                }catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
    }

}
