package com.leslia.mq.test;

import com.leslia.mq.QueueSender;
import com.leslia.mq.TopicSender;
import com.leslia.test.base.BaseTestLocal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/mq-sender.xml"})
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
