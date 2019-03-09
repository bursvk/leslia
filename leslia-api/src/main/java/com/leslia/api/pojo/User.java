package com.leslia.api.pojo;

import java.util.Date;

public class User implements java.io.Serializable {


    private long userId;

    private String username;

    private String password;

    private String salt;

    private Date createTime;




    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
