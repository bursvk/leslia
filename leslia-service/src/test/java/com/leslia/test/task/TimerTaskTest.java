package com.leslia.test.task;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {


    public static void main(String args[]){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run ");
            }
        }, 1000,1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " run1 ");
            }
        }, 1000);
    }


    public static void delay(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
