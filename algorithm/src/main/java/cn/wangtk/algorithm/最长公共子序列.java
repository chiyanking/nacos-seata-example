package cn.wangtk.algorithm;

import java.util.Arrays;

/**
 * 300.最长递增子序列
 * 674.最长连续递增序列
 * 718.最长重复子数组
 * 1143.最长公共子序列
 * 1035.不相交的线
 * 53.最大子序和
 * 392.判断子序列
 * 115.不同的子序列
 * 583.两个字符串的删除操作
 * 72.编辑距离
 * 为了绝杀编辑距离，我做了三步铺垫，你都知道么？
 * 647.回文子串
 * 516.最长回文子序列
 * 动态规划总结篇
 */
public class 最长公共子序列 {

    // 备忘录，消除重叠子问题
    static int[][] memo;

    public static void main(String[] args) {
        String s1 = "zabcde";
        String s2 = "acez";
        memo = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], 0);

        }
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    memo[i + 1][j + 1] = memo[i][j] + 1;
                } else {
                    //到s1的i位置和s2的j位置
                    //可能是s1的[1...i-1]加上s1[i]字符 和 s2[1....j]
                    //可能是s1的[1...i]              和 s2的[1...j-1]加上s2[j]字符来的
                    memo[i + 1][j + 1] = Math.max(memo[i + 1][j], memo[i][j + 1]);
                }
                System.out.println(s1.substring(0, i + 1) + " : " + s2.substring(0, j + 1) + " : " + memo[i + 1][j + 1]);
            }
        }
        System.out.println(memo[s1.length()][s2.length()]);


    }

    // 定义：计算 s1[i..] 和 s2[j..] 的最长公共子序列长度
    static int dp(String s1, int i, String s2, int j) {
        // base case
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        // 如果之前计算过，则直接返回备忘录中的答案
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 根据 s1[i] 和 s2[j] 的情况做选择
        if (s1.charAt(i) == s2.charAt(j)) {
            // s1[i] 和 s2[j] 必然在 lcs 中
            memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1);
        } else {
            // s1[i] 和 s2[j] 至少有一个不在 lcs 中
            memo[i][j] = Math.max(
                    dp(s1, i + 1, s2, j),
                    dp(s1, i, s2, j + 1)
            );
        }
        return memo[i][j];
    }
}