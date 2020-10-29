package com.wangtk.mybatis.algorithm;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();

        List<String> strings = generateParenthesis.generateParenthesis(4);
        System.out.println(strings);
    }

    /**
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, n, n, "");
        return res;
    }

    //
//    private void dfs(int left, int right, String curStr, List<String> res) {
//        if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
//            res.add(curStr);
//            return;
//        }
//
//        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
//            dfs(left - 1, right, curStr + "(", res);
//        }
//        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
//            dfs(left, right - 1, curStr + ")", res);
//        }
//    }
    public void dfs(List<String> res, int left, int right, String value) {
        if (left == 0 && right == 0) {
            res.add(value);
            return;
        }
        if (left > 0) {
            dfs(res, left - 1, right, value + "(");
        }
        if (right > left) {
            dfs(res, left, right - 1, value + ")");
        }
    }
}
