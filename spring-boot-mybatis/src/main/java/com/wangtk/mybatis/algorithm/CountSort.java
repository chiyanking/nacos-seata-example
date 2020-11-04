package com.wangtk.mybatis.algorithm;

import java.util.Arrays;
import java.util.Random;

public class CountSort {


    public static void main(String[] args) {
        int range = 5;
        int size = 30;
        Random random = new Random();
        int[] array = new int[size];
        for (int j = 0; j < size; j++) {
            array[j] = random.nextInt(range);
        }
        System.out.println(Arrays.toString(array));
        countSort(array, range);
    }

    public static void countSort(int[] array, int range) {
        int[] counts = new int[range];
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
