package cn.wangtk.algorithm;

import java.util.TreeSet;

public class 考场就座 {
    public static void main(String[] args) {
        考场就座 ch = new 考场就座(10);
        for (int i = 0; i < 10; i++) {
            ch.seat();
        }
    }

    // 2^31 2.1474836e+09（21亿），题目中1 <= N <= 10^9（10亿），所以int类型是符合要求的
    int N;
    TreeSet<Integer> students; // TreeSet是有序集合，有序无重复

    public 考场就座(int N) {
        this.N = N;
        students = new TreeSet<Integer>();
    }

    public int seat() {
        int student = 0;
        if (students.size() > 0) {
            int dist = students.first();
            Integer prev = null;
            for (Integer s : students) {
                if (prev != null) {
                    int d = (s - prev) / 2;
                    if (d > dist) {
                        dist = d;
                        student = prev + d;
                    }
                }
                prev = s;
            }
            if (N - 1 - students.last() > dist) {
                student = N - 1;
            }
        }
        students.add(student);
        return student;
    }

    public void leave(int p) {
        students.remove(p);
    }
}
