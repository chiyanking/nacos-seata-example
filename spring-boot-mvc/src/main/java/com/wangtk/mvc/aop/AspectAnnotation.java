package com.wangtk.mvc.aop;

import com.wangtk.mvc.annotation.CacheAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(10)
public class AspectAnnotation {

    @Pointcut("@annotation(com.wangtk.mvc.annotation.CacheAnnotation)")
    public void cacheAnnotationPointcut() {
    }

    @Around("cacheAnnotationPointcut()")
    public Object cacheAnnotationPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取method
        Method method = this.getSpecificmethod(joinPoint);
        // 获取注解
        CacheAnnotation cacheAnnotation = AnnotationUtils.findAnnotation(method, CacheAnnotation.class);
        CacheOperationInvoker aopAllianceInvoker = getCacheOperationInvoker(joinPoint);
        try {
            // 执行查询缓存方法
            return aopAllianceInvoker.invoke();

        } catch (Exception e) {
            return aopAllianceInvoker.invoke();
            // 忽略操作缓存过程中遇到的异常
        }
    }

    private Method getSpecificmethod(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // The method may be on an interface, but we need attributes from the
        // target class. If the target class is null, the method will be
        // unchanged.
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
        if (targetClass == null && pjp.getTarget() != null) {
            targetClass = pjp.getTarget().getClass();
        }
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        // If we are dealing with method with generic parameters, find the
        // original method.
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }

    private CacheOperationInvoker getCacheOperationInvoker(ProceedingJoinPoint joinPoint) {
        return () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable ex) {
                throw new CacheOperationInvoker.ThrowableWrapper(ex);
            }
        };
    }
}
