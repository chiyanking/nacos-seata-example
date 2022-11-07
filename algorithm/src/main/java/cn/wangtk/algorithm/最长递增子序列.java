package cn.wangtk.algorithm;

import java.util.Arrays;

public class 最长递增子序列 {


    public static void main(String[] args) {
        int i = new 最长递增子序列().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        System.out.println(i);
    }

    public int lengthOfLIS(int[] nums) {
        int[] memo = new int[nums.length];
        int max = 1;
        Arrays.fill(memo, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) { // 连续记录
                memo[i] = memo[i - 1] + 1;
                max = Math.max(memo[i], max);
            }
        }
        return max;
    }
}