package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.Stack;

public class 每日温度 {

    public static void main(String[] args) {
        int[] daily = new 每日温度().dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 });
        for (int i = 0; i < daily.length; i++) {
            System.out.print(daily[i] + " ");

        }

    }

    // 版本 1
    public int[] dailyTemperatures(int[] temperatures) {

        int lens = temperatures.length;
        int[] res = new int[lens];

        Arrays.fill(res, 0);

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (int i = 1; i < lens; i++) {
            while (!st.isEmpty() && Integer.compare(temperatures[i], temperatures[st.peek()]) > 0) {
                res[st.peek()] = i - st.peek();
                st.pop();
            }
            st.push(i);
        }

        return res;
    }
}
