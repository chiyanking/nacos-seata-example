package cn.wangtk.algorithm;

import java.util.Scanner;

public class 字符串比较 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.nextLine();
        String B = scanner.nextLine();
        String V = scanner.nextLine();

        char[] As = A.toCharArray();
        char[] Bs = B.toCharArray();
        int v = Integer.parseInt(V);

        int first = 0;
        int len = 0;
        int cha = 0;
        for (int i = 0; i < As.length - 1; i++) {
            int tempLast = 0;
            if (As[i + 1] == As[i] + 1) {
                tempLast = i + 1;
            } else {
                first = i + 1;
                continue;
            }

            for (int j = first; j <= tempLast - 1; j++) {
                if (Bs[j + 1] != Bs[j] + 1) {
                    cha = 0;
                    break;
                }

                if (j == first) {
                    cha += Math.abs(As[j] - Bs[j]);
                }
                cha += Math.abs(As[j + 1] - Bs[j + 1]);
            }

            int tempLen = tempLast - first + 1;
            if (cha <= v && tempLen > len) {
                len = tempLen;
                cha = 0;
            }
        }

        System.out.println(len);
    }
}