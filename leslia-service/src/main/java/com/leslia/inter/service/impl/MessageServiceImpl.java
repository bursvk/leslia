package com.leslia.inter.service.impl;

import com.leslia.mq.QueueSender;
import com.leslia.mq.TopicSender;
import com.leslia.inter.api.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
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
