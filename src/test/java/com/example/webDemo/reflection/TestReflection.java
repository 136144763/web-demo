package com.example.webDemo.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luofei on 2018/6/27.
 */
@Slf4j
public class TestReflection {

    @Test
    public void testReflection(){
        XYZ xyz=new XYZ();
        log.debug(XYZ.name);
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        Person person=new Person("luo",1);
        Class clazz=person.getClass();

        Field[] fields=clazz.getDeclaredFields();
        for (Field field:fields){
            String key=field.getName();
            PropertyDescriptor descriptor=new PropertyDescriptor(key,clazz);
            Method method=descriptor.getReadMethod();
            Object value=method.invoke(person);
            log.debug(key+":"+value);
        }
    }
}
