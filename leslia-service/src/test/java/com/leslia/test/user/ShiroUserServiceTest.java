package com.leslia.test.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leslia.ware.base.BaseTestDubbo;
import com.leslia.api.api.ShiroUserService;
import com.leslia.api.pojo.ShiroUser;
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
        logger.info("age"+user1.getAge());
    }

    @Test
    public void test2(){
        ShiroUser user=new ShiroUser();
        user.setUsername("user");
        user.setPassword("user");
        shiroUserService.delShiroUser(user);
    }



    @Test
    public void test1(){
        ShiroUser shiroUser=shiroUserService.getShiroUserByName("admin");
        logger.info(shiroUser.getRole());
    }

    @Test
    public void getUserById(){
        ShiroUser shiroUser= shiroUserService.getUserById("1");
        logger.info("username:"+shiroUser.getName()+",age:"+shiroUser.getAge());
    }

    @Test
    public void getUserById1(){
        String name=shiroUserService.getUserById1("1");
        logger.info("name:"+name);
    }

    @Test
    public void delUserById(){
        shiroUserService.delUserById("1");
    }


}
