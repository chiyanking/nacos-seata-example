package com.wangtk.mybatis.algorithm;

public class QuickSort {


    public static void main(String[] args) {

        int[] tmpArr = new int[]{2, 3, 4, 9, 1, 6, 4, 8, 7, 5};
        sort(tmpArr, 0, tmpArr.length - 1);
        for (int i = 0; i < tmpArr.length; i++) {
            System.out.print(tmpArr[i] + " ");
        }
    }

    /**
     * 2, 3, 4, 9, 1, 6, 4, 8, 7, 5
     * l                          r
     *          l                 r
     * 2, 3, 4, 5, 1, 6, 4, 8, 7, 9
     *          l        r
     * 2, 3, 4, 4, 1, 6, 5, 8, 7, 9
     *                l  r
     * 2, 3, 4, 4, 1, 5, 6, 8, 7, 9
     */
    public static void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int let = left;
        int rit = right;
        int base = arr[rit];
        while (let < rit) {
            while (let < rit && arr[let] <= base) {
                let++;
            }
            if (let < rit) {
                int temp = arr[let];
                arr[let] = arr[rit];
                arr[rit] = temp;
            }
            while (let < rit && arr[rit] > base) {
                rit--;
            }
            if (let < rit) {
                int temp = arr[let];
                arr[let] = arr[rit];
                arr[rit] = temp;
            }
        }
        if (left < right) {
            sort(arr, left, let - 1);
            sort(arr, let + 1, right);
        }
    }

}
