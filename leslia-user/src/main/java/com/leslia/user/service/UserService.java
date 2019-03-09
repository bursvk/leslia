package com.leslia.user.service;

import com.leslia.api.pojo.User;
import com.leslia.user.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


public class UserService {

    @Resource
    private static UserMapper userMapper;

    public static User getUser(String username,String password){
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return userMapper.getUserByParams(map);
    }

}
