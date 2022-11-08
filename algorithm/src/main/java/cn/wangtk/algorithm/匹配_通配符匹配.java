package cn.wangtk.algorithm;

public class 匹配_通配符匹配 {

    public static void main(String[] args) {

        boolean adceb = isMatch("", "");
        System.out.println(adceb);

    }

    static public boolean isMatch(String s, String p) {
        if ((s == p && p == null) || (s == "" && p == "")) {
            return true;
        }

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];


        dp[0][0] = true;
        if (p.charAt(0) == '*') {
            for (int i = 0; i <= s.length(); i++) {
                dp[i][1] = true;
            }
        }
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && j > 0) {
                dp[0][j + 1] = dp[0][j + 1] || dp[0][j];
            }

        }


        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*' && j > 0) {
                    dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j];
                }

                String s1 = i < s.length() - 1 ? s.substring(0, i + 1) : s;
                String s2 = j < p.length() - 1 ? p.substring(0, j + 1) : p;

                System.out.println(s1 + " " + s2 + " " + dp[i + 1][j + 1]);

            }

        }


        return dp[s.length()][p.length()];
    }


}