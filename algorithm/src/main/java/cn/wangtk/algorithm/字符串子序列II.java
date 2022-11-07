package cn.wangtk.algorithm;

import java.util.Scanner;

public class 字符串子序列II {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s1 = scan.nextLine();
        String s2 = scan.nextLine();
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len2 + 1][len1 + 1];
        for (int i = len2 - 1; i >= 0; i--) {
            for (int j = len1 - 1; j >= 0; j--) {
                if (s2.charAt(i) == s1.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        for (int i = len2 - 1; i >= 0; i--) {
            if (dp[i][0] == s1.length()) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}