package com.leslia.inter.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.leslia.inter.api.InitDemoService;
import org.springframework.stereotype.Component;


@Component
@Service
public class InitDemoServiceImpl implements InitDemoService {


    public String helloWorld(String name) {
        return "hello :"+name;
    }

}
