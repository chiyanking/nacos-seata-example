package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {

    public static void main(String[] args) {
        int[] nums = new int[]{
                -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4

        };


        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);

    }

    static public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> rest = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return rest;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    rest.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l - 1] == nums[l]) {
                        l++;
                    }
                    while (l < r && r + 1 < nums.length && nums[r + 1] == nums[r]) {
                        r--;
                    }
                } else if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                    while (l < r && r + 1 < nums.length && nums[r + 1] == nums[r]) {
                        r--;
                    }
                } else {
                    l++;
                    while (l < r && nums[l - 1] == nums[l]) {
                        l++;
                    }
                }

            }
        }

        return rest;

    }

}