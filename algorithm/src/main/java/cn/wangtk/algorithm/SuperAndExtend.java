package cn.wangtk.algorithm;

import java.util.List;

/**
 * 省去术语，目的是让读者先明白。java是单继承，所有继承的类构成一棵树。
 * 假设A和B都在一颗继承树里（否则super，extend这些词没意义）。
 * A super B 表示A是B的父类或者祖先，在B的上面。
 * A extend B 表示A是B的子类或者子孙，在B下面。
 * 由于树这个结构上下是不对称的，所以这两种表达区别很大。
 * 假设有两个泛型写在了函数定义里，作为函数形参（形参和实参有区别）：
 *  1) 参数写成：T<? super B>，对于这个泛型，?代表容器里的元素类型，由于只规定了元素必须是B的超类，
 *      导致元素没有明确统一的“根”（除了Object这个必然的根），所以这个泛型你其实无法使用它，对吧，除了把元素强制转成Object。
 *      所以，对把参数写成这样形态的函数，你函数体内，只能对这个泛型做插入操作，而无法读
 *  2) 参数写成： T<? extends B>，由于指定了B为所有元素的“根”，你任何时候都可以安全的用B来使用容器里的元素，但是插入有问题，
 *      由于供奉B为祖先的子树有很多，不同子树并不兼容，由于实参可能来自于任何一颗子树，所以你的插入很可能破坏函数实参，
 *      所以，对这种写法的形参，禁止做插入操作，只做读取
 */
public class SuperAndExtend {
    static public class Parent {


        private static int a;

    }

    static public class child extends Parent {


        private static int a;

    }


    public void getList(List<? extends Parent> list) {

        //list 提供值
        for (Parent parent : list) {
            System.out.println(parent);
        }

    }


    public void addList(List<? super Parent> list) {
        for (Object item : list) {
            // o 只能是Object类型，所以可以认为list不能提供值，因为提供的值拿不到类型
        }

        list.add(new child());
//        list.add(new Object());
    }


}
