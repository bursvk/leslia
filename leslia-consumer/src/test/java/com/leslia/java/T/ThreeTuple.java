package com.leslia.java.T;

public class ThreeTuple<A,B,C> {

    public final A a;

    public final B b;

    public final C c;

    public  ThreeTuple(A a,B b,C c){
        this.a=a;
        this.b=b;
        this.c=c;
    }

    public static void main(String args[]){
        ThreeTuple<Integer,Integer,String> threeTuple=new ThreeTuple<>(1,1,"aaa");
        System.out.println(threeTuple.a+""+threeTuple.b+""+threeTuple.c);
    }
}
