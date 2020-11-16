package com.wangtk.mybatis.types;

import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericInjectTest {
    private Service<Integer, String> abService;
    private Service<Long, Double> cdService;
    private List<List<String>> list;
    private Map<String, Map<String, Integer>> map;
    private List<String>[] array;

    private HashMap<Long, List<String>> method() {
        return null;
    }

    public GenericInjectTest(List<List<String>> list, Map<String, Map<String, Integer>> map) {

    }


    public static void main(String[] args) {

        ResolvableType resolvableType = ResolvableType.forField(ReflectionUtils.findField(GenericInjectTest.class, "cdService"));
        // 得到Service<C, D>第0个位置上的泛型实参类型，即C
        Class<?> resolve = resolvableType.getGeneric(0).resolve();
        System.out.println(resolve);


        //比如 List<List<String>> list是一种嵌套的泛型用例，我们可以通过如下操作获取嵌套的String类型:
        resolvableType = ResolvableType.forField(ReflectionUtils.findField(GenericInjectTest.class, "list"));
        Class<?> resolve1 = resolvableType.getGeneric(0).getGeneric(0).resolve();
        System.out.println(resolve1);


        // 比如Map<String, Map<String, Integer>> map我们想得到Integer，可以使用
        resolvableType = ResolvableType.forField(ReflectionUtils.findField(GenericInjectTest.class, "map"));
        resolvableType.getGeneric(1).getGeneric(1).resolve();
        Class<?> resolve2 = resolvableType.getGeneric(1, 1).resolve();
        System.out.println(resolve2);


        // 得到method方法的返回值 HashMap<Long, List<String>> method()，然后获得Map中List中的String泛型实参:
        resolvableType = ResolvableType.forMethodReturnType(ReflectionUtils.findMethod(GenericInjectTest.class, "method"));
        Class<?> returnGenericInject = resolvableType.getGeneric(1, 0).resolve();
        System.out.println(returnGenericInject);


        // 获取 CDService 继承自 Service 上面的范型
        ResolvableType as = ResolvableType.forClass(CDService.class).as(Service.class);
        ResolvableType[] generics = as.getGenerics();
        System.out.println("获取 CDService 继承自 Service 上面的范型 " + generics);
    }
}
