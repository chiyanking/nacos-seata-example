package com.wangtk.mvc.annotation;

import java.lang.reflect.Method;

public class AnnotationTest {
    public static void main(String[] args) throws SecurityException,
            NoSuchMethodException {

        Class<SubClass> clazz = SubClass.class;

        if (clazz.isAnnotationPresent(CacheAnnotation.class)) {
            CacheAnnotation cla = clazz.getAnnotation(CacheAnnotation.class);
            System.out.println("子类继承到父类类上Annotation,其信息如下：" + cla.value());
        } else {
            System.out.println("子类没有继承到父类类上Annotation");
        }

        // 实现抽象方法测试
        Method method = clazz.getMethod("abstractMethod", new Class[]{});
        if (method.isAnnotationPresent(CacheAnnotation.class)) {
            CacheAnnotation ma = method
                    .getAnnotation(CacheAnnotation.class);
            System.out.println("子类实现父类的abstractMethod抽象方法，继承到父类抽象方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类实现父类的abstractMethod抽象方法，没有继承到父类抽象方法中的Annotation");
        }

        //覆盖测试
        Method methodOverride = clazz.getMethod("doExtends", new Class[]{});
        if (methodOverride.isAnnotationPresent(CacheAnnotation.class)) {
            CacheAnnotation ma = methodOverride
                    .getAnnotation(CacheAnnotation.class);
            System.out
                    .println("子类继承父类的doExtends方法，继承到父类doExtends方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类继承父类的doExtends方法，没有继承到父类doExtends方法中的Annotation");
        }

        //继承测试
        Method method3 = clazz.getMethod("doHandle", new Class[]{});
        if (method3.isAnnotationPresent(CacheAnnotation.class)) {
            CacheAnnotation ma = method3
                    .getAnnotation(CacheAnnotation.class);
            System.out
                    .println("子类覆盖父类的doHandle方法，继承到父类doHandle方法中的Annotation,其信息如下：" + ma.value());
        } else {
            System.out.println("子类覆盖父类的doHandle方法，没有继承到父类doHandle方法中的Annotation");
        }
    }
}
