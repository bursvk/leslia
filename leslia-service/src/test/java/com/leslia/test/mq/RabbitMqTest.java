package com.leslia.test.mq;

import com.leslia.api.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class RabbitMqTest {


    private ApplicationContext context = null;
    private AmqpTemplate amqpTemplate= null;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("spring/rabbitmq.xml");
        amqpTemplate= (AmqpTemplate) context.getBean("rabbitTemplate");
    }

    @Test
    public void fanout() {
        amqpTemplate.convertAndSend("exchange.fanout","","hello world!");
    }

    @Test
    public void direct(){
        Map<String,Object> map=new HashMap<>();
        map.put("username","lucy");
        map.put("password","123456");
        amqpTemplate.convertAndSend("exchange.direct","direct",map);
    }

    @Test
    public void topic(){
        User user=new User();
        user.setUserId(1);
        user.setUsername("lucy");
        amqpTemplate.convertAndSend("exchange.topic","more.topic",user);
    }





}
