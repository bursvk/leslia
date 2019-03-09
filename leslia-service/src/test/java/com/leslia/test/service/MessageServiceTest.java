package com.leslia.test.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.MessageService;
import com.leslia.ware.base.BaseTestDubbo;
import org.junit.Test;


public class MessageServiceTest extends BaseTestDubbo {

    @Reference
    private MessageService messageService;

    @Test
    public void sendQueue(){
        messageService.sendQueue();
    }

    @Test
    public void sendTopic(){
        messageService.sendTopic();
    }

    @Test
    public void fanout(){
        messageService.fanout();
    }

    @Test
    public void direct(){
        messageService.direct();
    }

    @Test
    public void topic(){
        messageService.topic();
    }

}
