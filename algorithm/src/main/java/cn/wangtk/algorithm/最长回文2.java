package cn.wangtk.algorithm;

public class 最长回文2 {
    public static void main(String[] args) {

        String longestPalindrome = new 最长回文2().longestPalindrome("abbad");
        System.out.println(longestPalindrome);
    }

    public String longestPalindrome(String s) {

        char[] chr = s.toCharArray();

        boolean dp[][] = new boolean[chr.length][chr.length];

        // 初始化完对角线
        for (int i = 0; i < chr.length; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int len = 0;

        for (int j = 0; j < chr.length; j++) {
            for (int i = 0; i < j; i++) {
                if (chr[i] == chr[j]) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                        if (j - i > len) {
                            len = j - i;
                            start = i;
                        }
                    }
                    if (dp[i + 1][j - 1] == true) {
                        dp[i][j] = true;
                        if (j - i > len) {
                            len = j - i;
                            start = i;
                        }
                    }

                } else {
                    dp[i][j] = false;

                }
            }

        }

        if (len > 0) {
            return s.substring(start, start + len + 1);
        } else {
            return "";
        }
    }
}
