package com.leslia.service.mapper;

import com.leslia.api.pojo.User;

import java.util.Map;

public interface UserMapper {

    User getUserByParams(Map<String, Object> map);
}
