package com.leslia.api.api;

import com.leslia.api.pojo.ShiroUser;

public interface ShiroUserService {

    public ShiroUser getShiroUser(ShiroUser shiroUser);

    public ShiroUser getShiroUser1(ShiroUser shiroUser);

    public void delShiroUser(ShiroUser shiroUser);

    public ShiroUser getShiroUserByName(String userName);

    public ShiroUser getUserById(String userId);

    public String getUserById1(String userId);

    public void delUserById(String userId);



}
