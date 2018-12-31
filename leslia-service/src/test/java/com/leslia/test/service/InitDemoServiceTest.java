package com.leslia.test.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.test.base.BaseTestDubbo;
import com.leslia.api.api.InitDemoService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitDemoServiceTest extends BaseTestDubbo{

    private static Logger logger= LoggerFactory.getLogger(InitDemoServiceTest.class);

    @Reference
    private InitDemoService initDemoService;

    @Test
    public void helloWord(){
        String s=initDemoService.helloWorld("苏里");
        logger.error(s);
    }

    @Test
    public void desNum(){
       initDemoService.desNum();
    }

    @Test
    public void properties(){
        initDemoService.logProperties();
    }



}
