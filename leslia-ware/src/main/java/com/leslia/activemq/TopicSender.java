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
public class TopicSender {

    private Logger logger= LoggerFactory.getLogger(TopicSender.class);

    @Resource(name = "jmsTopicTemplate")
    private JmsTemplate jmsTemplate;

    public void send(String queueName,final String message){
        logger.info("主题名称：{}  发送消息：{}",queueName,message);
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }


}
