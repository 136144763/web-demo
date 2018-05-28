package com.example.webDemo.spring.annotation;

import java.lang.annotation.*;

/**
 * @author luofei on 2018/5/28.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";
}
