package cn.wangtk.algorithm;

import java.util.Arrays;

public class 零钱兑换 {

    public static void main(String[] args) {


        int i = coinChange(new int[]{2, 5}, 3);
        System.out.println(i);

    }

    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {  // 遍历背包
            for (int j = 0; j < coins.length; j++) { // 遍历物品
                if (i - coins[j] >= 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                    System.out.println("dp[i - coins[j]] " + dp[i - coins[j]] + " dp[i]:" + dp[i]);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }

    // static int dp(HashMap<Integer, List<Integer>> map, HashMap<Integer, Boolean>
    // visited, int cur, int depth) {

    // if (visited.get(cur) != null) {
    // return 0;
    // }
    // visited.put(cur, true);
    // int sum = 0;
    // List<Integer> list = map.get(cur);
    // if (list != null) {
    // for (int i = 0; i < list.size(); i++) {

    // sum += dp(map, visited, list.get(i), depth + 1);
    // }
    // }

    // return sum + depth * 2;
    // }
}
