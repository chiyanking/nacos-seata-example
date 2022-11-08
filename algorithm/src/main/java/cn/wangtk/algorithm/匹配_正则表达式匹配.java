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
                    // 如果 j 位置 是一个 *
                    // 看前一个元素 可以被匹配
                    //  0 次 前一个元素和*都删除掉 看下字符串是否匹配
                    //  1 次 不需要* 参与匹配 看下字符串是否匹配
                    //  n 次  如果前一位是. 那么组合 .* 能匹配任意长度的字符串 所以需要看下s[i-1]和s[j] 长度的字符串是否匹配
                    //        如果前一位是普通字符串
                    //        比如是a 那么是 a* 那么要看 s[i] 和 p[j-1] 是否匹配
                    //        如果匹配 那么消除掉s[i]元素 看下 s[i-1]是否和p[j]是否匹配
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