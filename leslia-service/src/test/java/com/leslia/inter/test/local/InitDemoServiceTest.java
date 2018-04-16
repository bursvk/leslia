package com.leslia.inter.test.local;

import com.leslia.base.test.BaseTestLocal;
import com.leslia.inter.api.InitDemoService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class InitDemoServiceTest extends BaseTestLocal{

    private static Logger logger= LoggerFactory.getLogger(InitDemoServiceTest.class);

    @Resource
    private InitDemoService initDemoService;

    @Test
    public void helloWord() throws Exception{
        String s=initDemoService.helloWorld("苏里");
        logger.info(s);
        System.in.read();
    }


}
