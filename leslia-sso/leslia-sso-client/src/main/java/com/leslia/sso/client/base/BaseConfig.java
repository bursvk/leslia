package com.leslia.sso.client.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class BaseConfig {


    private static Logger logger= LoggerFactory.getLogger(BaseConfig.class);

    private static String sso_url;


    static{
        ResourceBundle resourceBundle=ResourceBundle.getBundle("conf/config");
        sso_url=resourceBundle.getString("sso.url");
        logger.info("单点登录系统："+sso_url);
    }



    public static String getSso_url() {
        return sso_url;
    }

    public static void setSso_url(String sso_url) {
        BaseConfig.sso_url = sso_url;
    }




}
