package com.example.webDemo;

/**
 * Created by Administrator on 2018/4/13.
 */
public class Singleton {
    private Singleton() {
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
