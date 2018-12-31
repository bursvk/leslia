package com.leslia.java.sync;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class SyncTest {

    public static synchronized void a() throws Exception{
        System.out.println("a");
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static synchronized void b(){
        System.out.println("b");
    }


    public static void main(String args[]) throws Exception{
        //SyncTest syncTest=new SyncTest();

        new Thread (new Runnable() {
            @Override
            public void run()  {
                try {
                    System.out.println("a....");
                    Thread.sleep(Integer.MAX_VALUE);
                    //SyncTest.a();
                }catch (Exception e){

                }
            }
        }).start();
        System.out.println("main .... ");
        Thread.sleep(2);
        new Thread (new Runnable() {
            @Override
            public void run()  {
                try {
                    //SyncTest syncTest=new SyncTest();
                    SyncTest.b();
                }catch (Exception e){

                }
            }
        }).start();

    }


}
