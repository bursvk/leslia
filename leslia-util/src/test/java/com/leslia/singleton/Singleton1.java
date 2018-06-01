package com.leslia.singleton;

public class Singleton1 {
    private static Singleton1 ourInstance = new Singleton1();

    public static Singleton1 getInstance() {
        return ourInstance;
    }

    private Singleton1() {
    }
}
