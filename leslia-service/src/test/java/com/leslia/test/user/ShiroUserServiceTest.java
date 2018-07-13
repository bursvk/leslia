package com.leslia.test.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.test.base.BaseTestDubbo;
import com.leslia.user.api.ShiroUserService;
import com.leslia.user.pojo.ShiroUser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUserServiceTest extends BaseTestDubbo {

    private Logger logger= LoggerFactory.getLogger(ShiroUserServiceTest.class);

    @Reference
    private ShiroUserService shiroUserService;


    @Test
    public void test(){
        ShiroUser user=new ShiroUser();
        user.setUsername("user");
        user.setPassword("user");
        ShiroUser user1=shiroUserService.getShiroUser(user);
        logger.info(user1+"");
    }

    @Test
    public void test1(){
        ShiroUser shiroUser=shiroUserService.getShiroUserByName("admin");
        logger.info(shiroUser.getRole());
    }


}
