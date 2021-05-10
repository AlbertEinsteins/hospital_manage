package com.graduate.hospital_manage.exception;

import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @Autowired
    private LogUtils logUtils;

    @ExceptionHandler(value = MessageException.class)
    public Result myExceptionHandler(MessageException e) {
        e.printStackTrace();

        this.logUtils.writeLog(ELogLevel.INFO, e.getMessage(), "操作失败") ;
        return Result.FAILURE(e.getMessage()) ;
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Result runTimeException(RuntimeException e) {
        e.printStackTrace();

        this.logUtils.writeLog(ELogLevel.ERROR, e.getMessage()
                .substring(0, e.getMessage().length() > 30 ? 30: e.getMessage().length()),
                "系统内部错误") ;
        return Result.FAILURE("服务器错误") ;
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public Result runTimeException(BadCredentialsException e) {
        e.printStackTrace();
        this.logUtils.writeLog(ELogLevel.WARN, e.getMessage(), "账户用户名或密码错误") ;
        return Result.FAILURE("用户名或密码错误") ;
    }
}
