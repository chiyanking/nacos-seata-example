package cn.wangtk.algorithm;

import java.util.Arrays;

public class 最长连续递增子序列 {


    public static void main(String[] args) {
        int i = new 最长连续递增子序列().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
        System.out.println(i);
    }

    public int lengthOfLIS(int[] nums) {
        int[] memo = new int[nums.length];
        int maxvalue = 1;
        Arrays.fill(memo, 1);
        for (int i = 1; i < nums.length; i++) {
            int ma = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    ma = Math.max(memo[j], ma);
                }
            }
            memo[i] = ma + 1;
            maxvalue = Math.max(memo[i], maxvalue);
        }
        return maxvalue;
    }
}