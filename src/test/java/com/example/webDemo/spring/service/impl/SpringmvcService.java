package com.example.webDemo.spring.service.impl;

import java.util.Map;

/**
 * @author luofei on 2018/5/28.
 */
public interface SpringmvcService {
    int insert(Map map);

    int delete(Map map);

    int update(Map map);

    int select(Map map);
}
