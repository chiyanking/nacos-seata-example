package cn.wangtk.algorithm;

public class 匹配_正则表达式匹配 {

    public static void main(String[] args) {

        boolean abbc = new 匹配_正则表达式匹配().isMatch("aaa", "ab*ac*a");

        System.out.println(abbc);

    }

    public boolean isMatch(String s, String p) {

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        dp[0][0] = true;

        for (int j = 1; j < p.length(); j++) {
            if (p.charAt(j) == '*') {
                dp[0][j + 1] = dp[0][j - 1];
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {

                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*' && j > 0) {

                    if (p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1];
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || (p.charAt(j - 1) == s.charAt(i) && dp[i][j + 1]);
                    }
                }
                String s1 = i < s.length() - 1 ? s.substring(0, i + 1) : s;
                String s2 = j < p.length() - 1 ? p.substring(0, j + 1) : p;
                System.out.println(s1 + " " + s2 + " " + dp[i + 1][j + 1]);

            }
        }
        return dp[s.length()][p.length()];

    }

}