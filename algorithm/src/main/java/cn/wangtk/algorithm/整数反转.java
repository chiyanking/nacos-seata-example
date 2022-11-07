package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 整数反转 {

    public static void main(String[] args) {
        System.out.println(reverse(-12345));

    }

    static public int reverse(int x) {
        String valueOf = String.valueOf(x);

        if (valueOf.contains("-")) {
            valueOf = valueOf.replace("-", "");
        }


        List<Character> collect = valueOf.chars().mapToObj(it -> Character.valueOf((char) it)).collect(Collectors.toList());

        Collections.reverse(collect);


        System.out.println(collect);
        if (x < 0) {
            collect.add(0, '-');
        }

        int val = 0;
        try {
            val = Integer.parseInt(collect.stream().map(String::valueOf).collect(Collectors.joining()));
        } catch (Exception e) {

        }

        return val;

    }

}