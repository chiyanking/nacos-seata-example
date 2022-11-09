package cn.wangtk.algorithm;


public class L_53_最大子数组和 {


    public static void main(String[] args) {

        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

    }

    static public int maxSubArray(int[] nums) {
        //前i位最大连续数组组合
        int[] dp = new int[nums.length];
        int max = nums[0];

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] + nums[i] < nums[i]) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);

        }
        return max;
    }
}