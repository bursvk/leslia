package com.leslia.mq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component
public class QueueListener implements MessageListener {

    private Logger logger= LoggerFactory.getLogger(QueueListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("队列名称：{}  监听消息：{}","service.queue",((TextMessage)message).getText());
        }catch (JMSException e){
            e.printStackTrace();
        }
    }

}
