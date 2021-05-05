package com.graduate.hospital_manage.annotation.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Systemlog {
    //操作描述
    String value() default "" ;

    //操作状态、具体操作内容细节等等
    String operate() default "" ;

}
