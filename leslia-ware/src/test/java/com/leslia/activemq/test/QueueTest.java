package com.leslia.activemq.test;

import com.leslia.activemq.QueueSender;
import com.leslia.activemq.TopicSender;
import com.leslia.base.test.BaseTestLocal;
import org.junit.Test;

import javax.annotation.Resource;

public class QueueTest extends BaseTestLocal {

    @Resource
    private QueueSender queueSender;
    @Resource
    private TopicSender topicSender;

    @Test
    public void sendQueue1(){
        queueSender.send("queue1","hello queue1");
    }

    @Test
    public void sendQueue2(){
        queueSender.send("queue2","hello queue2");
    }

    @Test
    public void sendTopic1(){
        topicSender.send("topic1","hello topic1");
    }

    @Test
    public void sendTopic2(){
        topicSender.send("topic2","hello topic2");
    }



}
