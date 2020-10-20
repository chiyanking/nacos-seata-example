package com.wangtk.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Aspect1Service {
    @Pointcut("execution(public * com.wangtk.mvc.service..*(..))")
    public void cacheService() {
    }

    @Before("cacheService()")
    public void doCacheService() {
        log.info("cache data !");
    }

}
