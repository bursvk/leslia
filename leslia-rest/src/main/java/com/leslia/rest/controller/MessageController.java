package com.leslia.rest.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.MessageService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/MQ")
public class MessageController{

    @Reference
    private MessageService messageService;

    @Resource
    private JmsTemplate jmsQueueTemplate;

    @Resource
    private JmsTemplate jmsTopicTemplate;


    @RequestMapping("/sendQueue")
    @ResponseBody
    public void sendQueue(){
        messageService.sendQueue();
    }

    @RequestMapping("/sendTopic")
    @ResponseBody
    public void sendTopic(){
        messageService.sendTopic();
    }

    @RequestMapping("/sendMessage/queue")
    @ResponseBody
    public void sendQueueRest(){
        jmsQueueTemplate.convertAndSend("rest.queue","message send in rest");
    }

    @RequestMapping("/sendMessage/topic")
    @ResponseBody
    public void sendTopicRest(){
        jmsTopicTemplate.convertAndSend("rest.topic","message send in rest");
    }


}
