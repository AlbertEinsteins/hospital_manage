package com.graduate.hospital_manage.annotation.aop.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduate.hospital_manage.annotation.aop.Cache;
import com.graduate.hospital_manage.utils.RedisClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class CacheHandler {
    private Logger logger = LoggerFactory.getLogger(CacheHandler.class) ;

    @Autowired
    private RedisClient redisClient ;

    @Value("${redis.hashkey}")
    private String cacheKey ;

    @Autowired
    private ObjectMapper objectMapper ;

    @Pointcut("@annotation(com.graduate.hospital_manage.annotation.aop.Cache)")
    public void cache() { }


    /**
     * 对加有@Cache注解的方法返回值进行缓存
     * @param proceedingJoinPoint 切入点，可以获取方法签名
     * @return 方法的返回值
     * @throws Throwable
     */
    @Around("cache()")
    public Object doAround (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        if(method.isAnnotationPresent(Cache.class)) {
            Cache cache = method.getDeclaredAnnotation(Cache.class) ;
            logger.info("keyname: {}, expireTime: {}", cache.value(), cache.expireTime()) ;

            if(redisClient.hHasKey(cacheKey, cache.value())) {
                return objectMapper.readValue(
                        (String)redisClient.hget(cacheKey, cache.value()),
                        List.class
                ) ;
            }

            Object result = proceedingJoinPoint.proceed();
            String jsonStr = objectMapper.writeValueAsString(result);
            if(cache.expireTime() > 0) {
                redisClient.hset(cacheKey, cache.value(),
                        jsonStr, cache.expireTime()) ;
            } else {
                redisClient.hset(cacheKey, cache.value(), jsonStr) ;
            }
            return result ;
        }

        return proceedingJoinPoint.proceed() ;
    }

}
