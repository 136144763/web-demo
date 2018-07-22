package com.example.webDemo.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author luofei on 2018/6/27.
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object proxyed;

    public DynamicProxyHandler(Object proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理工作了.");
        return method.invoke(proxyed, args);
    }
}
