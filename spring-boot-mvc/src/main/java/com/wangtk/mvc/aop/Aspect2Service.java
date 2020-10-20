package com.wangtk.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Aspect2Service {
    @Pointcut("execution(public * com.wangtk.mvc.service..*(..))")
    public void logService() {
    }

    @Before("logService()")
    public void doLogService() {
        log.info("log service !");
    }

}
