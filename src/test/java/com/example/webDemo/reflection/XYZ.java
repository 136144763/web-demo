package com.example.webDemo.reflection;

/**
 * @author luofei on 2018/6/27.
 */
public class XYZ {

    public static String name = "luoxn28";

    static {
        System.out.println("xyz静态块");
    }

    public XYZ() {
        System.out.println("xyz构造了");
    }

}
