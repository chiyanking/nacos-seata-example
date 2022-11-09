package cn.wangtk.algorithm;


public class L_32_最长有效括号 {


    public static void main(String[] args) {

        int longk = longestValidParentheses("()");
        System.out.println(longk);

    }

    static public int longestValidParentheses(String s) {

        int max = 0;


        int[] dp = new int[s.length()];
        //为什么从1开始呢 因为第0元素不管是左括号还是右括号最长有效长度都是0
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else {
                //是右括号
                int preleft = i - 1 - dp[i - 1];//找到能匹配的左括号
                if (preleft >= 0 && s.charAt(preleft) == '(') {
//                  有效括号
                    dp[i] = dp[i - 1] + 2 + (preleft - 1 < 0 ? 0 : dp[preleft - 1]);

                    max = Math.max(max, dp[i]);
                }
            }
        }


        return max;
    }
}