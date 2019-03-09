package com.leslia.util.crypt;

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
                return AESUtil.aesDecode(AESKey.CONFIG_FILE,propertyValue);
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }


    public static void main(String[] args) {
        String[] keys = { "Insight111." };
        System.out.println("key | AESEncode | AESDecode");
        for (String key : keys) {
            System.out.print(key + " | ");
            String encryptString = AESUtil.aesEncode(AESKey.CONFIG_FILE,key);
            System.out.print(encryptString + " | ");
            String decryptString = AESUtil.aesDecode(AESKey.CONFIG_FILE,encryptString);
            System.out.println(decryptString);
        }
    }


}
