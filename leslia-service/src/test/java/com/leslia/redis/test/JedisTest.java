package com.leslia.redis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {


    @Test
    public void test(){
        Jedis jedis=RedisManager.getJedis();
        jedis.auth("Leslia000");
        String name=jedis.get("name");
        System.out.println(name);
    }


}
