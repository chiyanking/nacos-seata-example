package com.wangtk.mybatis.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(lists);
        int i = threeSum.threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        System.out.println(i);

    }

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
     * [-5,-3,8]
     *
     * -5 -5 -3 -3 -3 4 5 6 8 9
     *  i  s                  e
     *  i     s               e
     *  i     s             e
     *  i             s   e
     *  i             s e
     *     i  s               e
     *     i  s             e
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2) {
            if (nums[i] > 0) {
                break;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    lists.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                    end--;
                    while (start < end && nums[start] == nums[start - 1]) {
                        start++;
                    }
                    while (start < end && nums[end] == nums[end + 1]) {
                        end--;
                    }
                } else if (sum < 0) {
                    start++;
                } else if (sum > 0) {
                    end--;
                }
            }
            i++;
            while (i < nums.length - 2 && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return lists;
    }

    /**
     * nums = [-1,2,1,-4], target = 1
     * 与 target 最接近的和是 2 (-1 + 2 + 1 = 2)
     * 3 <= nums.length <= 10^3
     * -10^3 <= nums[i] <= 10^3
     * -10^4 <= target <= 10^4
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return Integer.MAX_VALUE;
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;


        for (int i = 0; i < nums.length - 2; i++) {

            if (nums[i] > target) {
                return min;
            }
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                /**
                 *  target - (nums[i] + nums[start] + nums[end])
                 */
                int temp = Math.abs(target - (nums[i] + nums[start] + nums[end]));
                int i1 = target - temp;
                if (temp < min) {
                    min = temp;
                }
                if (i1 == 0) {
                    return 0;
                } else if (i1 < 0) {
                    start++;
                } else if (i1 > 0) {
                    end--;
                }
            }
        }
        return min;
    }
}
