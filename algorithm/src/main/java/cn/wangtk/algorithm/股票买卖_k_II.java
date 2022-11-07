package cn.wangtk.algorithm;

public class 股票买卖_k_II {

    public static void main(String[] args) {
        System.out.println(new 股票买卖_k_II().maxProfit_k_II(new int[] { 3, 2, 6, 5, 0, 3 }));
        System.out.println(new 股票买卖_k_II().maxProfit_k_II(new int[] { 7, 6, 4, 3, 1 }));
    }

    // 原始版本
    int maxProfit_k_II(int[] prices) {

        // 第i天 0 未持有 1 持有
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }
}
