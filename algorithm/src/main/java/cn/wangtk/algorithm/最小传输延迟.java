package cn.wangtk.algorithm;

public class 最小传输延迟 {

    public static void main(String[] args) {
        System.out.println(new 最小传输延迟().getMin(new int[][] {
                { 1, 5, 1, 5 },
                { 4, 1, 5, 1 },
                { 4, 5, 5, 1 }
        }));

    }

    public int getMin(int[][] intvs) {
        int row = intvs.length, col = intvs[0].length;

        int[][] dp = new int[row][col];

        int k = 0;
        dp[0][0] = intvs[0][0];
        for (int i = 1; i < row; i++) {
            if (intvs[i][0] == intvs[i - 1][0]) {
                k++;
            }
            dp[i][0] = dp[i - 1][0] + intvs[i][0] - k;
            k = 0;
        }

        for (int i = 1; i < col; i++) {
            if (intvs[0][i] == intvs[0][i - 1]) {
                k++;
            }
            dp[0][i] = dp[0][i - 1] + intvs[0][i] - k;
            k = 0;
        }
        int top = 0, left = 0, left_top = 0;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (intvs[i][j] == intvs[i - 1][j]) {
                    top++;
                }
                if (intvs[i][j] == intvs[i][j - 1]) {
                    left++;
                }
                if (intvs[i][j] == intvs[i - 1][j - 1]) {
                    left_top++;
                }
                int dp_top = intvs[i][j] + dp[i - 1][j] - top;
                int dp_left = intvs[i][j] + dp[i][j - 1] - left;
                int dp_lef_top = intvs[i][j] + dp[i - 1][j - 1] - left_top;
                top = 0;
                left = 0;
                left_top = 0;

                dp[i][j] = Math.min(dp_top, Math.min(dp_left, dp_lef_top));

            }
        }

        return dp[row - 1][col - 1];
    }

}
