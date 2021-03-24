package com.graduate.hospital_manage.exception;

import com.graduate.hospital_manage.response.Result;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = MessageException.class)
    public Result myExceptionHandler(MessageException e) {
        e.printStackTrace();
        return Result.FAILURE(e.getMessage()) ;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Result runTimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.FAILURE("服务器错误") ;
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public Result runTimeException(BadCredentialsException e) {
        e.printStackTrace();
        return Result.FAILURE("用户名或密码错误") ;
    }
}
