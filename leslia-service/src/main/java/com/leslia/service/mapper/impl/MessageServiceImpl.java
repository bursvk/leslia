package com.leslia.service.mapper.impl;

import com.leslia.api.api.MessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.Resource;


public class MessageServiceImpl implements MessageService {

    @Resource
    private JmsTemplate jmsQueueTemplate;
    @Resource
    private JmsTemplate jmsTopicTemplate;
    @Resource
    private AmqpTemplate amqpTemplate;


    @Override
    public void sendQueue() {
        jmsQueueTemplate.convertAndSend("service.queue","the module leslia-service test");
    }

    @Override
    public void sendTopic() {
        jmsTopicTemplate.convertAndSend("service.topic","the module leslia-service test");
    }

    @Override
    public void fanout() {
        amqpTemplate.convertAndSend("exchange.fanout","","hello world");
    }

    @Override
    public void direct() {
        amqpTemplate.convertAndSend("exchange.direct","direct","hello world");
    }

    @Override
    public void topic() {
        amqpTemplate.convertAndSend("exchange.topic","more.topic","hello world");
    }




}
