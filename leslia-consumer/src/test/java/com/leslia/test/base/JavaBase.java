package com.leslia.test.base;

public class JavaBase {

    public String userName="user";

    public String password="user";


    public static void main(String args[]){
        User user=new User();
        String username=user.userName;
        String password=user.password;
        System.out.println(username);
        System.out.println(password);
        user.aa();
    }

}
