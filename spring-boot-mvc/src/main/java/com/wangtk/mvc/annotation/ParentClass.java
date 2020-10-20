package com.wangtk.mvc.annotation;

@CacheAnnotation(value = "类名上的注解")
public abstract class ParentClass {

    @CacheAnnotation(value = "父类的abstractMethod方法")
    public abstract void abstractMethod();

    @CacheAnnotation(value = "父类的doExtends方法")
    public void doExtends() {
        System.out.println(" ParentClass doExtends ...");
    }

    @CacheAnnotation(value = "父类的doHandle方法")
    public void doHandle() {
        System.out.println(" ParentClass doHandle ...");
    }
}