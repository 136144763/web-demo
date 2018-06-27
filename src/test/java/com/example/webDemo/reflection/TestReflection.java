package com.example.webDemo.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
    public void test(){

    }
}
