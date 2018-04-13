package com.example.webDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2018/4/13.
 */
@Slf4j
public class Teacher extends Person {
    public Teacher() {
        super();
    }

    public Teacher(int a) {
        System.out.println(a);
    }

    public void func() {
        System.out.println("2,");
    }

    public static void main(String[] args) {
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher(3);
    }
}
