package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class 寻找两个正序数组的中位数 {

    public static void main(String[] args) {
        String convert = new 寻找两个正序数组的中位数().convert("PAYPALISHIRING", 3);
        System.out.println(convert);
    }

    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }

        String[] list = new String[numRows];

        Arrays.fill(list, "");
        int start = 0;
        int val = 1;

        for (int i = 0; i < s.length(); i++) {

            list[start] += s.charAt(i);

            start += val;
            if (start == 0 || start == numRows - 1) {
                val = -val;
            }
        }
        return Arrays.asList(list).stream().collect(Collectors.joining(""));

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i = 0;

        int mid = (nums1.length + nums2.length) / 2;

        while (nums1.length > 0 && nums2.length > 0) {

            int mid1 = nums1.length / 2;

            int mid2 = nums2.length / 2;

            if (nums1[mid1] < nums2[mid2]) {

            }

        }

        return 1d;

    }
}