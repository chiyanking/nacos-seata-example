package cn.wangtk.algorithm;

public class 正则表达式匹配 {

    public static void main(String[] args) {

        boolean abbc = new 正则表达式匹配().isMatch("abbcc", "a.*c");

        System.out.println(abbc);

    }

    public boolean isMatch(String s, String p) {

        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];

        for (int j = 1; j < p.length(); j++) {
            for (int i = 1; i < s.length(); i++) {



            }

        }

        return memo[s.length() - 1][p.length()];

    }

}