package com.wangtk.mybatis.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSums(int[] nums) {

        List<List<Integer>> nlist = new ArrayList<>();
        Arrays.sort(nums);


        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {

                if (nums[j] + nums[k] < -nums[i]) {
                    j++;
                } else if (nums[j] + nums[k] > -nums[i]) {
                    k--;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    nlist.add(list);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }

                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }

        }

        return nlist;
    }

    /***
     *
     *
     * -10 -9 -3 3 4 5 6 8 9
     *
     *
     *
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return null;
        }
        ArrayList<List> lists = new ArrayList<>();

        Arrays.sort(nums);
        int f = 0;
        int s = 1;
        int l = nums.length - 1;

        while (true) {
            if (f > l) {
                break;
            }
            int sum = nums[f] + nums[s] + nums[l];
            if (sum == 0) {
                lists.add(Arrays.asList(nums[f], nums[s], nums[l]));
            }


        }
    }
}
