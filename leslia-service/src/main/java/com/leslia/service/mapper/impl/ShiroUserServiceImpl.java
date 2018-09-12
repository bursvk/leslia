package com.leslia.service.mapper.impl;

import com.leslia.api.api.ShiroUserService;
import com.leslia.api.pojo.ShiroUser;
import com.leslia.service.mapper.ShiroUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Transactional
public class ShiroUserServiceImpl implements ShiroUserService {


    private Logger logger= LoggerFactory.getLogger(ShiroUserServiceImpl.class);
    @Resource
    private ShiroUserMapper shiroUserMapper;


    @Cacheable(value="UserCache", key="#shiroUser.username")
    @Override
    public ShiroUser getShiroUser(ShiroUser shiroUser){
        logger.info("params;username={},password={}",shiroUser.getUsername(),shiroUser.getPassword());
        return shiroUserMapper.getShiroUser(shiroUser);
    }

    @CachePut(value="UserCache", key="#shiroUser.username")
    @Override
    public ShiroUser getShiroUser1(ShiroUser shiroUser){
        logger.info("params;username={},password={}",shiroUser.getUsername(),shiroUser.getPassword());
        return shiroUserMapper.getShiroUser(shiroUser);
    }

    @CacheEvict(value="UserCache", key="#userId")
    @Override
    public void delShiroUser(ShiroUser shiroUser){
        logger.info("");
        logger.info("params;username={},password={}",shiroUser.getUsername(),shiroUser.getPassword());

    }


    @Override
    public ShiroUser getShiroUserByName(String userName) {
        logger.info("params:username={}",userName);
        return shiroUserMapper.getShiroUserByUserName(userName);
    }

    @Cacheable(value="UserCache", key="#userId")
    @Override
    public ShiroUser getUserById(String userId) {
        logger.info("查询用户 userId:"+userId);
        ShiroUser shiroUser=new ShiroUser();
        shiroUser.setAge("19");
        shiroUser.setName("suli");
        return shiroUser;
    }

    @CachePut(value="UserCache", key="#userId")
    @Override
    public String getUserById1(String userId) {
      /*  try {
            Thread.sleep(6000);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        logger.info("查询用户1 userId"+userId);
        return "suli";
    }

    @CacheEvict(value="UserCache", key="#userId")
    @Override
    public void delUserById(String userId) {
        logger.info("删除记录......");
    }

}
