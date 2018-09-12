package com.leslia.test.user;

import com.leslia.test.base.BaseTestLocal;
import com.leslia.service.mapper.ShiroUserMapper;
import com.leslia.api.pojo.ShiroUser;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class ShiroUserMapperTest extends BaseTestLocal{

    private Logger logger= LoggerFactory.getLogger(ShiroUserMapperTest.class);

    @Resource
    private ShiroUserMapper shiroUserMapper;

    @Test
    public void test(){
        ShiroUser user=new ShiroUser();
        user.setUsername("admin");
        user.setPassword("admin");
        ShiroUser shiroUser=shiroUserMapper.getShiroUser(user);
        logger.info(shiroUser+"");
    }


}
