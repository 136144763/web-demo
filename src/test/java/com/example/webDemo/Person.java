package com.example.webDemo;

/**
 * Created by Administrator on 2018/4/13.
 */
public class Person {
    private String name;
    private int gender;
    private int age;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender == 0 ? "man" : "woman";
    }

    public void work() {
        if (18 <= age && age <= 50) {
            System.out.println(name + " is working very hard!");
        } else {
            System.out.println(name + " can't work any more!");
        }
    }

    public static void main(String[] args) {
        Person person=new Person();
        person.gender=1;
        System.out.println(person.age);
    }
}
