package com.wangtk.mybatis.algorithm;

public class Jump {

    public static void main(String[] args) {
        Jump jump = new Jump();
        int jump1 = jump.jump(new int[]{
                2, 3, 1, 1, 4, 4, 2, 1, 2, 1, 1, 3, 2
        });

        System.out.println(jump1);

        int i = jump.JumpFloor(4);
        System.out.println(i);
    }

    public int JumpFloor(int target) {
        int count = 0;
        if (target <= 0)
            return count;
        else if (target == 1)
            return 1;
        else if (target == 2)
            return 2;
        else {
            count = JumpFloor(target - 1) + JumpFloor(target - 2);
            return count;
        }
    }


    public int jump(int[] nums) {
        int n = nums.length - 1;
        int steps = 0, position = 0;
        while (position < n) {
            if (position + nums[position] >= n) { //可以直接跳到终点
                steps++;
                break;
            } else {
                int max = 0;
                int maxi = 0;
                for (int i = 1; i <= nums[position]; i++) {//选择下次能跳到最远位置的下标
                    int maxJump = i + nums[position + i];
                    if (max < maxJump) {
                        max = maxJump;
                        maxi = i;
                    }
                }
                position += maxi;
                steps++;
            }
        }
        return steps;
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 示例:
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * [2,3,1,1,4,4,2,1,2,1,1,3,2]
     */
//    public int jump(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int step = 0;
//        int lastPos = nums.length - 1;
//        for (int i = nums.length - 1; i >= 0; i--) {
//            int jump = nums[i];
//            if (i + jump >= lastPos && nums[i] >= nums[lastPos]) {
//                lastPos = i;
//                step++;
//            }
//        }
//        return step;
//    }
}
