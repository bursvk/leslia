package com.leslia.base.test;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dubbo_consumer.xml"})
public class BaseTestDubbo {

    private static Logger logger= LoggerFactory.getLogger(BaseTestDubbo.class);

    @Before
    public void before(){
        logger.info("Dubbo服务测试开始........");
    }

    @After
    public void end(){
        logger.info("Dubbo服务测试结束........");
    }


}
