package com.example.webDemo.spring.annotation;

import java.lang.annotation.*;

/**
 * @author luofei on 2018/5/28.
 */
@Target({ElementType.FIELD})//注解的注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Quatifier {
    String value() default "";
}
