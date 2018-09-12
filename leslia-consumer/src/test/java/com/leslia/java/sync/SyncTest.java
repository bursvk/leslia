package com.leslia.java.sync;

public class SyncTest {

    public static synchronized    void a() throws Exception{
        System.out.println("a");
        Thread.sleep(Integer.MAX_VALUE);
    }

    public static synchronized void b(){
        System.out.println("b");
    }


    public static void main(String args[]) throws Exception{

        new Thread (new Runnable() {
            @Override
            public void run()  {
                try {
                    SyncTest syncTest=new SyncTest();
                    syncTest.a();
                }catch (Exception e){

                }
            }
        }).start();
        Thread.sleep(2);
        new Thread (new Runnable() {
            @Override
            public void run()  {
                try {
                    SyncTest syncTest=new SyncTest();
                    syncTest.b();
                }catch (Exception e){

                }
            }
        }).start();

    }


}
