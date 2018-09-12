package com.leslia.test.task;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {

    @Test
    public void test1(){
        for (int i = 0; i < 10; ++i) {
            new Timer("timer - " + i).schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " run ");
                }
            }, 1000);
        }
    }

    public static void main(String args[]){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run ");
            }
        }, 1000,1000);
    }



}
