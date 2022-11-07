package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.List;

public class 最接近的三数之和 {

    public static void main(String[] args) {
        int[] nums = new int[]{
                -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4

        };
        int value = threeSumClosest(nums, 1);

        System.out.println(value);

    }

    static public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int min_target = Integer.MAX_VALUE;
        int value = 0;

        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {

                int min = (nums[i] + nums[l] + nums[r]);
                if (min > target) {

                    if (min - target < min_target) {
                        min_target = min - target;
                        value = min;
                    }
                    r--;
                    while (l < r && r + 1 < nums.length && nums[r + 1] == nums[r]) {
                        r--;
                    }
                } else if (min < target) {
                    if (target - min < min_target) {
                        min_target = target - min;
                        value = min;
                    }
                    l++;
                    while (l < r && nums[l - 1] == nums[l]) {
                        l++;
                    }
                } else {
                    return min;
                }

            }
        }

        return value;

    }

}