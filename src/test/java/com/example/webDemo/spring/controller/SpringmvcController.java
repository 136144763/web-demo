package com.example.webDemo.spring.controller;

import com.example.webDemo.spring.annotation.Controller;
import com.example.webDemo.spring.annotation.RequestMapping;
import com.example.webDemo.spring.service.impl.MyService;
import com.example.webDemo.spring.service.impl.SpringmvcService;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luofei on 2018/5/28.
 */
@Controller("wuqi")
public class SpringmvcController {

    @Qualifier("MyServiceImpl")
    MyService myService;

    @Qualifier("SpringmvcServiceImpl")
    SpringmvcService springmvcService;

    @RequestMapping("insert")
    public String insert(HttpServletRequest request, HttpServletResponse response,String param){
        myService.insert(null);
        springmvcService.insert(null);
        return null;
    }

    @RequestMapping("delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.delete(null);
        springmvcService.delete(null);
        return null;
    }

    @RequestMapping("update")
    public String update(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.update(null);
        springmvcService.update(null);
        return null;
    }

    @RequestMapping("select")
    public String select(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.select(null);
        springmvcService.select(null);
        return null;
    }




}
