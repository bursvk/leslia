package com.leslia.test.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    public static void main(String args[]){
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        for (int i = 0; i < 10; ++i) {
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " run ");
                }
            } , 1 , TimeUnit.SECONDS);
        }
        executor.shutdown();
    }
}
