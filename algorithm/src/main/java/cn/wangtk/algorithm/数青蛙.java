package cn.wangtk.algorithm;

public class 数青蛙 {

    public static void main(String[] args) {
        System.out.println(new 数青蛙().minNumberOfFrogs("croakcroak"));
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int max = 0;

        int c = 0;
        int r = 0;
        int o = 0;
        int a = 0;
        int k = 0;

        for (int i = 0; i < croakOfFrogs.length(); i++) {
            Character tmp = croakOfFrogs.charAt(i);
            if (tmp == 'c') {
                c++;
                max = Math.max(max, c);
            }
            if (tmp == 'r') {
                r++;
                if (r > c) {
                    return -1;
                }
            }
            if (tmp == 'o') {
                o++;
                if (o > r) {
                    return -1;
                }
            }
            if (tmp == 'a') {
                a++;
                if (a > o) {
                    return -1;
                }
            }
            if (tmp == 'k') {
                k++;
                if (k > a) {
                    return -1;
                }
                c--;
                r--;
                o--;
                a--;
                k--;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0 || k != 0) {
            return -1;
        }

        return max;
    }
}
