package com.leslia.service.mapper;

import com.leslia.api.pojo.ShiroUser;

public interface ShiroUserMapper {

  public  ShiroUser getShiroUser(ShiroUser shiroUser);

  public  ShiroUser getShiroUserByUserName(String username);

}
