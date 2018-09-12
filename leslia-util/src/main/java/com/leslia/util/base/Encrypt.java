package com.leslia.util.base;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class Encrypt extends PropertyPlaceholderConfigurer {


    private String[] propertyNames = {
            "jdbc.password","redis.password"
    };
    /**
     * 解密指定propertyName的加密属性值
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        for (String p : propertyNames) {
            if (p.equalsIgnoreCase(propertyName)) {
                return AESUtil.aesDecode(propertyValue);
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }


}
