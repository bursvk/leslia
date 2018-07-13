package com.leslia.user.api;

import com.leslia.user.pojo.ShiroUser;

public interface ShiroUserService {

    public ShiroUser getShiroUser(ShiroUser shiroUser);

    public ShiroUser getShiroUserByName(String userName);

}
