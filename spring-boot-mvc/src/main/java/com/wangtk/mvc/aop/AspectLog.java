package com.wangtk.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class AspectLog {
    @Pointcut("execution(public * com.wangtk.mvc.service..*(..))")
    public void logService() {
    }

    @Before("logService()")
    public void doLogService() {
        log.info("log service !");
    }

}
