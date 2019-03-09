package com.leslia.ware.lock;

public class ThreadA implements Runnable {

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }

}
