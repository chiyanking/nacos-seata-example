package com.wangtk.security.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectController {
    @Pointcut("  execution(public * *(..))")
    public void joinPointCutOnController() {
    }

    @Before("joinPointCutOnController()")
    public void doAccessCheck() {
        log.info("发送数据");
    }

}
