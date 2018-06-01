package com.leslia.singleton;

import com.leslia.redis.test.RedisManager;
import redis.clients.jedis.Jedis;

public class StartInit {

    public static void main(String args[]){
        Jedis jedis= RedisManager.getJedis();
        jedis.auth("Leslia000");
        String name=jedis.get("name");
        System.out.println(name);
    }

}
