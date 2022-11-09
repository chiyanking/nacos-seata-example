package cn.wangtk.algorithm;


public class L_27_移除元素 {


    public static void main(String[] args) {

        removeElement(new int[]{1}, 3);


    }

    static public int removeElement(int[] nums, int val) {

        int left = 0;
        int right = nums.length;


        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}