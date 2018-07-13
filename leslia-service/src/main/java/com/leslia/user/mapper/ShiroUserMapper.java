package com.leslia.user.mapper;

import com.leslia.user.pojo.ShiroUser;

public interface ShiroUserMapper {

  public  ShiroUser getShiroUser(ShiroUser shiroUser);

  public  ShiroUser getShiroUserByUserName(String username);

}
