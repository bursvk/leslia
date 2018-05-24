package com.leslia.inter.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.leslia.inter.api.InitDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Service
public class InitDemoServiceImpl implements InitDemoService {

    private Logger logger= LoggerFactory.getLogger(InitDemoServiceImpl.class);

    public String helloWorld(String name) {
        logger.info("name:{}",name);
        return "hello :"+name;
    }

}
