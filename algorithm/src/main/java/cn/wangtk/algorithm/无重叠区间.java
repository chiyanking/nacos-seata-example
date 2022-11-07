package cn.wangtk.algorithm;

import java.util.Arrays;

public class 无重叠区间 {

    public static void main(String[] args) {
        System.out.println(new 无重叠区间().eraseOverlapIntervals(new int[][] {
                { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 }
        }));

    }

    public int eraseOverlapIntervals(int[][] intvs) {
        
        if (intvs.length == 0)
            return 0;
        // 按 end 升序排序
        Arrays.sort(intvs, (a, b) -> {
            return a[1] - b[1];
        });
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int x_end = intvs[0][1];
        for (int[] interval : intvs) {
            int start = interval[0];
            if (start >= x_end) {
                // 找到下一个选择的区间了
                count++;
                x_end = interval[1];
            }
        }
        return  count;
    }

}
