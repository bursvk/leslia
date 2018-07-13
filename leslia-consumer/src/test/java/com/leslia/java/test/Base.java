package com.leslia.java.test;


import org.springframework.stereotype.Component;

@Component
public interface Base {

    default void getUsername(){
        System.out.println("suli");
    }

    public  String  getPassword();



}
