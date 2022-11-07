package cn.wangtk.algorithm;

import java.util.Arrays;

public class 找零钱 {
    public static void main(String[] args) {
        System.out.println(new 找零钱().coinChange(new int[] { 1, 2, 5 }, 100));
    }

    public int coinChange(int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int[] meno = new int[amount + 1];

        Arrays.fill(meno, -666);
        meno[0] = 0;

        int tmp = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int value = dp(coins, amount - coins[i], meno);
            if (value == -1) {
                continue;
            }
            tmp = Math.min(value, tmp);
        }
        return tmp == Integer.MAX_VALUE ? -1 : tmp + 1;

    }

    int dp(int[] coins, int amount, int[] meno) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (meno[amount] != -666) {
            return meno[amount];
        }

        int tmp = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int value = dp(coins, amount - coins[i], meno);
            if (value == -1) {
                continue;
            }
            tmp = Math.min(value, tmp);
        }
        meno[amount] = tmp == Integer.MAX_VALUE ? -1 : tmp + 1;

        return meno[amount];
    }

}
