package com.example.webDemo.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author luofei on 2018/6/27.
 */
@Slf4j
public class BaseTest {

    class Base{}
    class Derived extends Base { }

    @Test
    public void testBase(){
        Base base=new Derived();
        if(base instanceof Derived){
            // 这里可以向下转换了
            log.debug("ok");
        }else {
            log.debug("not ok");
        }
    }
}
