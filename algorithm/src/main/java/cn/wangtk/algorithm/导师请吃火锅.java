package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class 导师请吃火锅 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] x = new int[n][2];
            for (int i = 0; i < n; i++) {
                x[i][0] = sc.nextInt();
                x[i][1] = sc.nextInt();
            }
            // 设置一个数组，存放每道菜可以吃到的时间。
            int[] arrTime = new int[n];
            for (int i = 0; i < n; i++) {
                arrTime[i] = x[i][0] + x[i][1];
            }
            // 对数组进行从小到大进行排序，这样便于后面比较计算
            Arrays.sort(arrTime);

            // 新建一个数组，和数组arrTime对应，用于记录每道菜是否可以吃到，可以吃到标记加1.
            int[] arrCount = new int[n];

            int next = 0;
            arrCount[0] = 1;
            for (int i = 1; i < n; i++) {
                if (arrTime[i] >= (arrTime[next] + m)) {
                    arrCount[i] = 1;
                    next = i;
                }
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (arrCount[i] > 0) {
                    count++;
                }
            }

            // 遍历输出结果
            // for (int a : arrCount) {
            // System.out.println(a);
            // }

            System.out.println(count);

        }
    }
}