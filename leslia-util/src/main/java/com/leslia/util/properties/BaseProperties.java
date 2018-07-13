package com.leslia.util.properties;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class BaseProperties {


    private Properties properties;

    private InputStream inputStream;

    public BaseProperties(String resources){
        properties=new Properties();
        inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(resources);
        try {
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void close(){
        try {
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
