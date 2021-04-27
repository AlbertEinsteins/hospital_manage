package com.graduate.hospital_manage.annotation.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    String value() default "key" ;

    int expireTime() default -1 ;
}
