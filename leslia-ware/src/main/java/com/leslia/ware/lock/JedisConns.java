package com.leslia.ware.lock;

import com.leslia.ware.conf.RedisConf;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConns {

    private static JedisPool jedisPool;

    static{
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(RedisConf.getMaxTotal());
        // 设置最大空闲数
        config.setMaxIdle(RedisConf.getMaxIdle());
        // 设置最大等待时间
        config.setMaxWaitMillis(RedisConf.getMaxWaitMillis());
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        config.setTestOnBorrow(RedisConf.isTestOnBorrow());
        jedisPool = new redis.clients.jedis.JedisPool(config, RedisConf.getHost(), RedisConf.getPort(), RedisConf.getTimeout(),RedisConf.getPassword());
    }

    public static JedisPool getJedisPool(){
        return jedisPool;
    }



}
