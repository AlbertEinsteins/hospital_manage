package com.graduate.hospital_manage.annotation.aop.handler;

import com.graduate.hospital_manage.annotation.aop.Systemlog;
import com.graduate.hospital_manage.model.Log;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.utils.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Aspect
//@Component
public class LogHandler {
    @Autowired
    private LogUtils logUtils ;


    @Pointcut("execution(public * com.graduate.hospital_manage.controller.*.*(..))")
    public void pointCut() { }

    /**
     * 根据返回值的状态Success，Failure等，记录日志级别
     * 根据注解元信息记录操作内容
     * @param rtn 方法返回值
     */
    @AfterReturning(value = "pointCut()", returning = "rtn")
    public void logAfterInvoke(JoinPoint joinPoint, Result rtn) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod() ;

        if(method.isAnnotationPresent(Systemlog.class) && rtn != null) {
            Systemlog logAnno = method.getDeclaredAnnotation(Systemlog.class);

            if(rtn.isSuccess()) {
                this.logUtils.writeLog(ELogLevel.INFO,
                        logAnno.value(), logAnno.operate());
            } else {
                this.logUtils.writeLog(ELogLevel.WARN,
                        logAnno.value(), logAnno.operate());
            }
        }
    }
}
