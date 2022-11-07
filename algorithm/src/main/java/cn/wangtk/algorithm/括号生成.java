package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.List;

public class 括号生成 {

    public static void main(String[] args) {

        for (String generateParenthesis : generateParenthesis(3)) {

            System.out.println(generateParenthesis);
        }


    }

    static public List<String> generateParenthesis(int n) {
        List<String> rest = new ArrayList<>();
        dfs(rest, "", 0, 0, n);
        return rest;
    }

    static void dfs(List<String> res, String prefix, int left, int right, int n) {
        if (left == n && right == left) {
            res.add(prefix);
        }

        if (left > n) {
            return;
        }
        if (right > left) {
            return;
        }
//        System.out.println(prefix);
        dfs(res, prefix + "(", left + 1, right, n);
        dfs(res, prefix + ")", left, right + 1, n);
    }

}