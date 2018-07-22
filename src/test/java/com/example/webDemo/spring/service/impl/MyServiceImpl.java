package com.example.webDemo.spring.service.impl;

import com.example.webDemo.spring.annotation.Service;

import java.util.Map;

/**
 * @author luofei on 2018/5/28.
 */
@Service("MyServiceImpl")
public class MyServiceImpl implements MyService{
    @Override
    public int insert(Map map) {
        System.out.println("MyServiceImpl:"+"insert");
        return 0;
    }

    @Override
    public int delete(Map map) {
        System.out.println("MyServiceImpl:"+"delete");
        return 0;
    }

    @Override
    public int update(Map map) {
        System.out.println("MyServiceImpl:"+"update");
        return 0;
    }

    @Override
    public int select(Map map) {
        System.out.println("MyServiceImpl:"+"select");
        return 0;
    }
}
