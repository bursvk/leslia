package com.leslia.test.inter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.api.api.MessageService;
import com.leslia.test.base.BaseTestDubbo;
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

}
