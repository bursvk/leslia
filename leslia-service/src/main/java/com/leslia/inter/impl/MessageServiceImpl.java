package com.leslia.inter.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.leslia.activemq.QueueSender;
import com.leslia.activemq.TopicSender;
import com.leslia.inter.api.MessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
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
