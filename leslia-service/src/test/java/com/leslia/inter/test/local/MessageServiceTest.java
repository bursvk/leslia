package com.leslia.inter.test.local;

import com.leslia.base.test.BaseTestLocal;
import com.leslia.inter.api.MessageService;
import org.junit.Test;

import javax.annotation.Resource;

public class MessageServiceTest extends BaseTestLocal {

    @Resource
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
