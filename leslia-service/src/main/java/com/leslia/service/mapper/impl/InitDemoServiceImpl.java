package com.leslia.service.mapper.impl;

import com.leslia.api.api.InitDemoService;
import com.leslia.util.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public class InitDemoServiceImpl implements InitDemoService {

    private Logger logger= LoggerFactory.getLogger(InitDemoServiceImpl.class);

    @Value("${redis.hostName}")
    private String redis_hostName;
    @Value("${redis.password}")
    private String redis_password;

    int n=500;
    public String helloWorld(String name) {
        logger.info("name:{}", name);
        return "hello :" + name;
    }

    public void desNum(){
        --n;
        System.out.println(n);
        throw new BaseException();
    }

    public void logProperties(){
        logger.info("redis_hostName:"+redis_hostName);
        logger.info("redis_password:"+redis_password);
    }

    @Override
    public void getList(List a) {
        logger.info("a:{}",a);
    }
}
