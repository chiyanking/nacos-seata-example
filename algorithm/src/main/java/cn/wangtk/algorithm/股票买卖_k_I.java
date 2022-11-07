package cn.wangtk.algorithm;

public class 股票买卖_k_I {

    public static void main(String[] args) {
        System.out.println(new 股票买卖_k_I().maxProfit_k_1(new int[] { 3, 2, 6, 5, 0, 3 }));
    }

    // 原始版本
    int maxProfit_k_1(int[] prices) {

        // 第i天 0 未买卖 1 第一次买 2 第一次卖
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
        }
        return dp[n - 1][2];
    }
}
