package cn.wangtk.algorithm;


public class L_55_跳跃游戏 {


    public static void main(String[] args) {

        int[] nums = new int[]{2, 3, 1, 1, 4};
        canJump(nums);
    }

    static public boolean canJump(int[] nums) {


        boolean[] dp = new boolean[nums.length];
        dp[0] = true;

        for (int i = 0; i < nums.length && dp[i] == true; i++) {
            for (int j = i; j <= i + nums[i]; j++) {
                if (j > nums.length - 1) {
                    dp[nums.length - 1] = true;
                    return true;
                } else {
                    dp[j] = true;
                }
            }

        }

        return dp[nums.length - 1];
    }
}