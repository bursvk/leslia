package com.leslia.ware.lock;

import java.util.UUID;

public class Service {


    int n = 500;

    public void seckil() {
        // 返回锁的value值，供释放锁时候进行判断
        RedisLock redisLock=new RedisLock();
        String requestId= UUID.randomUUID().toString();
        try {
            if(redisLock.lock("lock",requestId)){
                --n;
                System.out.println(n);
            }
        }finally {
            redisLock.reLock("lock",requestId);
        }
    }

    public void seckill(){
        ZookeeperLock lock   = new ZookeeperLock("lock");
        lock.lock();
        --n;
        System.out.println(n);
        lock.unlock();
    }

    public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            new Thread(new ThreadA(service)).start();
        }
    }



}
