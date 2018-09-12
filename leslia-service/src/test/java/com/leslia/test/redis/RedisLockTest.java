package com.leslia.test.redis;

import com.leslia.ware.conf.RedisConf;
import org.junit.Test;

public class RedisLockTest {

    @Test
    public void redisLock(){
        int port= RedisConf.getPort();
        System.out.println(port);
    }
}
