package com.leslia.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class QueueSender {

    private Logger logger= LoggerFactory.getLogger(QueueSender.class);

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    public void send(String queueName,final String message){
        logger.info("队列名称：{}  发送消息：{}",queueName,message);
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
