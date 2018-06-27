package com.example.webDemo.reflection;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author luofei on 2018/6/27.
 */
@Slf4j
public class Person implements Serializable{

    public String name;

    public int age;

    public Person(String name, int age) {
        this.name=name;
        this.age=age;
    }

}
