package com.leslia.user.service.impl;

import com.leslia.user.api.ShiroUserService;
import com.leslia.user.mapper.ShiroUserMapper;
import com.leslia.user.pojo.ShiroUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class ShiroUserServiceImpl implements ShiroUserService {


    private Logger logger= LoggerFactory.getLogger(ShiroUserServiceImpl.class);
    @Resource
    private ShiroUserMapper shiroUserMapper;



    public ShiroUser getShiroUser(ShiroUser shiroUser){
        logger.info("params;username={},password={}",shiroUser.getUsername(),shiroUser.getPassword());
        return shiroUserMapper.getShiroUser(shiroUser);
    }


    @Override
    public ShiroUser getShiroUserByName(String userName) {
        logger.info("params:username={}",userName);
        return shiroUserMapper.getShiroUserByUserName(userName);
    }



}
