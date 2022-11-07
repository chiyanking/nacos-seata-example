package cn.wangtk.algorithm;

import java.util.LinkedList;
import java.util.Scanner;

public class 贪吃蛇 {
    static char[][] arr = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            char[] ch = sc.nextLine().replaceAll(" ", "").toCharArray();
            int N = sc.nextInt();
            int M = sc.nextInt();
            arr = new char[N][M];
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = sc.next().charAt(0);
                }
            }
            fun(arr, ch);
        }
    }

    static void fun(char[][] arr, char[] ch) {
        LinkedList<int[]> snake = new LinkedList<>();
        int[] start = new int[2];
        // 找到起点
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 'H') {
                    start[0] = i;
                    start[1] = j;
                    break;
                }
            }
        }
        // 起点入队
        snake.add(start);
        // 遍历操作列表
        for (int i = 0; i < ch.length; i++) {
            int flag = 0;
            if (ch[i] == 'U') {
                flag = 1;
                continue;
            } else if (ch[i] == 'D') {
                flag = -1;
                continue;
            } else if (ch[i] == 'L') {
                flag = -2;
                continue;
            } else if (ch[i] == 'R') {
                flag = 2;
                continue;
            } else if (ch[i] == 'G') {
                switch (flag) {
                    case 1:
                        start[0]++;
                        break;
                    case -1:
                        start[0]--;
                        break;
                    case 2:
                        start[1]++;
                        break;
                    case -2:
                        start[1]--;
                        break;
                }
                if (isEnd(snake, start)) {
                    break;
                }
                // 吃到，只加不减
                if (arr[start[0]][start[1]] == 'F') {
                    snake.addFirst(start);
                } else {
                    // 没吃到，加头去尾
                    snake.addFirst(start);
                    snake.removeLast();
                }
            }
        }
        System.out.println(snake.size());

    }

    static boolean isEnd(LinkedList<int[]> list, int[] a) {
        // 撞墙了
        if (a[0] >= arr.length || a[1] >= arr[0].length)
            return true;
        // 撞到自己了
        for (int[] i : list) {
            if (i[0] == a[0] && i[1] == a[1])
                return true;
        }
        return false;
    }

}