package com.leslia.ware.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * redis 组件
 * 锁：主要问题 需要设置锁过期时间
 */
public class RedisLock {

    private static Logger logger= LoggerFactory.getLogger(RedisLock.class);


    //锁过期时间
    private static final int expireTime=360000;
    //获取锁超时时间
    private static final long acquireTimeout=50000;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;



        /**
         * 尝试获取分布式锁
         * @param lockKey 锁
         * @param requestId 请求标识
         * @return 是否获取成功
         */
    public  boolean lock(String lockKey, String requestId) {
        Jedis conn = JedisConns.getJedisPool().getResource();
        long endTime = System.currentTimeMillis() + acquireTimeout;
        while(System.currentTimeMillis()<endTime){
            String result = conn.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                logger.info("线程：{}获取到锁,lock_key:{}",Thread.currentThread().getName(),lockKey);
                return true;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.error("线程：{}获取锁超时,lock_key:{}",Thread.currentThread().getName(),lockKey);
        return false;
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     */
    public  boolean reLock(String lockKey, String requestId) {
        Jedis conn = JedisConns.getJedisPool().getResource();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = conn.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            logger.info("线程：{}解锁成功,lock_key:{}",Thread.currentThread().getName(),lockKey);
            return true;
        }
        logger.error("线程：{}解锁失败,lock_key:{}",Thread.currentThread().getName(),lockKey);
        return false;
    }

}
