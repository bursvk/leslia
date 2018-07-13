package com.leslia.test.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/redis.xml"})
public class BaseTestLocal {

    private static Logger logger= LoggerFactory.getLogger(BaseTestLocal.class);

    @Before
    public void before(){
        logger.info("本地服务测试开始........");
    }

    @After
    public void end(){
        logger.info("本地服务测试结束........");
    }

    @Test
    public void test(){

    }

}
