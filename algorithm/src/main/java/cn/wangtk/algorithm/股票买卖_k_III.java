package cn.wangtk.algorithm;

public class 股票买卖_k_III {

    public static void main(String[] args) {
        System.out.println(new 股票买卖_k_III().maxProfit_k_III(new int[] { 3, 2, 6, 5, 0, 3 }));
    }

    // 原始版本
    int maxProfit_k_III(int[] prices) {
        int n = prices.length;
        // 0 未操作 1 第一次买入 2 第一次卖出 3 第二次买入 4 第二次卖出
        int[][] dp = new int[n][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = 0 ;
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[n - 1][4];
    }
}
