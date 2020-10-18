package com.wangtk.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectService {
    @Pointcut("execution(public * com.wangtk.mvc..*(..))")
    public void serviceCache() {
    }

    @Before("serviceCache()")
    public void doAccessCheck() {
        log.info("发送数据");
    }

}
