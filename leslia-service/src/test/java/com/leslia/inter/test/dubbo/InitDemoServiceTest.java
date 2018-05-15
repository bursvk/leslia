package com.leslia.inter.test.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.base.test.BaseTestDubbo;
import com.leslia.inter.api.InitDemoService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class InitDemoServiceTest extends BaseTestDubbo {

    private static Logger logger= LoggerFactory.getLogger(InitDemoServiceTest.class);

    @Reference
    private InitDemoService initDemoService;

    @Test
    public void helloWord() throws IOException{
        String s=initDemoService.helloWorld("苏里");
        logger.info(s);
        System.in.read();
    }


}
