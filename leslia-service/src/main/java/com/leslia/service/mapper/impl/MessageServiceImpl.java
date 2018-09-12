package com.leslia.service.mapper.impl;

import com.leslia.api.api.MessageService;
import com.leslia.ware.mq.QueueSender;
import com.leslia.ware.mq.TopicSender;

import javax.annotation.Resource;


public class MessageServiceImpl implements MessageService {

    @Resource
    private QueueSender queueSender;
    @Resource
    private TopicSender topicSender;

    @Override
    public void sendQueue() {
        queueSender.send("service.queue","the module leslia-service test");
    }

    @Override
    public void sendTopic() {
        topicSender.send("service.topic","the module leslia-service test");
    }


}
