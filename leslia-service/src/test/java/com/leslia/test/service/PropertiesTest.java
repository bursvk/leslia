package com.leslia.test.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application.xml"})
public class PropertiesTest {


    @Value("${jdbc.username}")
    private String jdbc_username;
    @Value("${jdbc.password}")
    private String jdbc_password;
    @Value("${redis.hostName}")
    private String redis_hostName;
    @Value("${redis.port}")
    private String redis_port;
    @Value("${redis.password}")
    private String redis_password;


    @Test
    public void test(){
       System.out.println("jdbc.username:"+jdbc_username);
       System.out.println("jdbc.password:"+jdbc_password);
       System.out.println("redis_hostName:"+redis_hostName);
       System.out.println("redis_post:"+redis_port);
       System.out.println("redis_password:"+redis_password);
    }


}
