package com.xufree.learning.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhangmingxu ON 10:13 2019-05-05
 **/

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamConfig {
    String name();
    boolean required() default false;
}
