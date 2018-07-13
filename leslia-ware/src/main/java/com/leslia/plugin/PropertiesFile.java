package com.leslia.plugin;

import java.util.ResourceBundle;

public class PropertiesFile {

    public static void main(String args[]){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("conf/redis");
        String hostname=resourceBundle.getString("redis.hostName");
        System.out.println(hostname);
    }


}
