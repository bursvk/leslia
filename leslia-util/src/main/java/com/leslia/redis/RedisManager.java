package com.leslia.redis;

import redis.clients.jedis.Jedis;

public class RedisManager {


    private static Jedis Jedis = new Jedis("39.105.98.191",6379);

    public static Jedis getJedis() {
        return Jedis;
    }

    private RedisManager() {
    }



}
