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



public class QueueListener implements MessageListener {

    private Logger logger= LoggerFactory.getLogger(QueueListener.class);

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
                    logger.info("队列名称：{}  监听消息：{}",message.getJMSDestination(),((TextMessage)message).getText());
                    redisUtil.hset("queue",message.getJMSDestination().toString(),((TextMessage)message).getText());
                }catch (JMSException e){
                        e.printStackTrace();
                }
            }
        });
    }

}
