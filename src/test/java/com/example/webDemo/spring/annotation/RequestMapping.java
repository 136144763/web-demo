package com.example.webDemo.spring.annotation;

import java.lang.annotation.*;

/**
 * @author luofei on 2018/5/28.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
