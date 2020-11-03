package com.wangtk.mybatis.algorithm;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }

    /**
     * lllc
     * llc
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (needle.length() > haystack.length())
            return -1;
        int j = 0;//needle指针
        int i = 0;
        for (i = 0; i < haystack.length(); ++i) {
            if (j == needle.length()) {//判断完成
                return i - needle.length();
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            } else {
                i = i - j;
                j = 0;
            }
        }
        if (j == needle.length()) {//判断完成
            return i - needle.length();
        }
        return -1;
    }

}
