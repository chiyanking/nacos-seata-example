package cn.wangtk.algorithm;


import java.util.Arrays;

public class L_45_跳跃游戏_II {


    public static void main(String[] args) {

        int[] nums = new int[]{2, 3, 1, 1, 4};
        int jump = jump(nums);
        System.out.println(jump);
    }

    static public int jump(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < nums.length && dp[i] != Integer.MAX_VALUE; i++) {

            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (j >= nums.length - 1) {
                    dp[nums.length - 1] = dp[i] + 1;
                    return dp[nums.length - 1];
                } else {
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
            }
        }

        return dp[nums.length - 1];
    }
}