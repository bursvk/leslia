package com.leslia.ware.conf;

import com.leslia.util.base.AESUtil;

import java.util.ResourceBundle;

public class RedisConf {

    //主机地址
    private static String host;
    //端口号
    private static int port;
    //客户端超时时间单位是毫秒
    private static int timeout;
    //密码
    private static String password;
    //控制一个pool可分配多少个jedis实例
    private static int maxTotal;
    //最大空闲数
    private static int maxIdle;
    //最大建立连接等待时间
    private static long maxWaitMillis;
    //是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
    private static boolean testOnBorrow;

   /* static {
        BaseProperties properties=new BaseProperties("conf/redis.properties");
        host=properties.getProperty("redis.hostName");
        port=Integer.parseInt(properties.getProperty("redis.port"));
        timeout=Integer.parseInt(properties.getProperty("redis.timeout"));
        password=properties.getProperty("redis.password");
        password= AESUtil.aesDecode(password);
        maxTotal=Integer.parseInt(properties.getProperty("redis.maxTotal"));
        maxIdle=Integer.parseInt(properties.getProperty("redis.maxIdle"));
        maxWaitMillis=Long.parseLong(properties.getProperty("redis.maxWaitMillis"));
        testOnBorrow=Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow"));
        properties.close();
    }*/

    static{
        ResourceBundle resourceBundle=ResourceBundle.getBundle("conf/lock-redis");
        host=resourceBundle.getString("redis.hostName");
        port=Integer.parseInt(resourceBundle.getString("redis.port"));
        timeout=Integer.parseInt(resourceBundle.getString("redis.timeout"));
        password=resourceBundle.getString("redis.password");
        password= AESUtil.aesDecode(password);
        maxTotal=Integer.parseInt(resourceBundle.getString("redis.maxTotal"));
        maxIdle=Integer.parseInt(resourceBundle.getString("redis.maxIdle"));
        maxWaitMillis=Long.parseLong(resourceBundle.getString("redis.maxWaitMillis"));
        testOnBorrow=Boolean.parseBoolean(resourceBundle.getString("redis.testOnBorrow"));
        System.out.println("redis 地址："+port);
    }


    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        RedisConf.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        RedisConf.port = port;
    }

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        RedisConf.timeout = timeout;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        RedisConf.password = password;
    }

    public static int getMaxTotal() {
        return maxTotal;
    }

    public static void setMaxTotal(int maxTotal) {
        RedisConf.maxTotal = maxTotal;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static void setMaxIdle(int maxIdle) {
        RedisConf.maxIdle = maxIdle;
    }

    public static long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public static void setMaxWaitMillis(long maxWaitMillis) {
        RedisConf.maxWaitMillis = maxWaitMillis;
    }

    public static boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public static void setTestOnBorrow(boolean testOnBorrow) {
        RedisConf.testOnBorrow = testOnBorrow;
    }
}
