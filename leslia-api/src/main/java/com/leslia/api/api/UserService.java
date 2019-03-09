package com.leslia.api.api;

import com.leslia.api.pojo.User;

public interface UserService {

    User getUser(String username,String password);

}
