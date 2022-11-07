package cn.wangtk.algorithm;

import java.util.Objects;
import java.util.Stack;

public class 最大括号深度 {
    public static void main(String[] args) {
        String operator = "([)]";
        String[] ol = operator.split(",");
        Stack<String> sk = new Stack<>();
        int max = 0;
        for (int i = 0; i < ol.length; i++) {
            String op = ol[i];
            if (Objects.equals(op, "(")) {
                sk.push(")");
                max = Math.max(max, sk.size());
            } else if (Objects.equals(op, "{")) {
                sk.push("}");
                max = Math.max(max, sk.size());
            } else if (Objects.equals(op, "[")) {
                sk.push("]");
                max = Math.max(max, sk.size());
            } else {
                if (sk.size() == 0 || !Objects.equals(op, sk.pop())) {
                    System.out.println("错误");
                }
            }
        }
        System.out.println(max);
    }

}
