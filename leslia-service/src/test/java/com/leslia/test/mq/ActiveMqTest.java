package com.leslia.test.mq;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;

public class ActiveMqTest {


    private ApplicationContext context = null;
    private JmsTemplate jmsQueueTemplate= null;
    private JmsTemplate jmsTopicTemplate= null;

    @Before
    public void setUp(){
        context = new ClassPathXmlApplicationContext("spring/activemq.xml");
        jmsQueueTemplate= (JmsTemplate) context.getBean("jmsQueueTemplate");
        jmsTopicTemplate= (JmsTemplate) context.getBean("jmsTopicTemplate");
    }

    @Test
    public void queue(){
        jmsQueueTemplate.convertAndSend("service.queue","hello world");
    }

    @Test
    public void topic(){
        jmsTopicTemplate.convertAndSend("service.topic","hello world");
    }

    @Test
    public void queueObject(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",1);
        map.put("username","lucy");
        jmsQueueTemplate.convertAndSend("service.queue.object",map);
    }


}
