package com.wangtk.mybatis.algorithm;

import java.util.Arrays;
import java.util.Random;

public class CountSort {


    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[10];
        for (int j = 0; j < 10; j++) {
            array[j] = random.nextInt(5);
        }
        System.out.println(Arrays.toString(array));
        countSort(array);
    }

    public static void countSort(int[] array) {
        int[] counts = new int[5];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]] = counts[array[i]] + 1;
        }
        for (int i = 1; i < 5; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }
        Integer[] tempArray = new Integer[array.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = array[i];
        }
        // 整理设置
        for (int i = tempArray.length - 1; i >= 0; i--) {
            int value = tempArray[i];
            int pos = counts[value] - 1;
            array[pos] = value;
            counts[value] -= 1;
            System.out.println("value " + value + " : pos " + pos);
            System.out.println(Arrays.toString(array));
        }
    }
}
