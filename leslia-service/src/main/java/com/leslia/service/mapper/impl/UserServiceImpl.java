package com.leslia.service.mapper.impl;

import com.leslia.api.api.UserService;
import com.leslia.api.pojo.User;
import com.leslia.service.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User getUser(String username,String password){
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return userMapper.getUserByParams(map);
    }


}
