package com.leslia.singleton;

public class Singleton {

    private Singleton(){
    }

    private volatile static Singleton singleton;

   /* private static class SingletonHolder{
        public static  Singleton instance=new Singleton();
    }

    public Singleton newInstance(){
        return SingletonHolder.instance;
    }
*/

   public static Singleton newInstance(){
       if(singleton==null){
           synchronized (Singleton.class){
                if(singleton==null){
                    singleton=new Singleton();
                }
           }
       }
       return singleton;
   }




}
