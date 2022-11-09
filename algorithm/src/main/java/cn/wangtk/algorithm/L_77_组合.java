package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L_77_组合 {

    public static void main(String[] args) {
        List<List<Integer>> combine = combine(4, 2);
        System.out.println(combine);

    }


    static public List<List<Integer>> combine(int n, int k) {


        List<List<Integer>> rest = new ArrayList<>();


        LinkedList<Integer> list = new LinkedList<>();


        boolean dp[] = new boolean[n + 1];

        dp(dp, list, rest, n, k, 1);


        return rest;

    }

    private static void dp(boolean[] dp, LinkedList<Integer> list, List<List<Integer>> rest, int n, int k, int start) {

        if (list.size() == k) {
            rest.add(new LinkedList(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (dp[i] == false) {
                dp[i] = true;
                list.add(i);
                dp(dp, list, rest, n, k, i + 1);
                list.removeLast();
                dp[i] = false;
            }
        }
    }
}
