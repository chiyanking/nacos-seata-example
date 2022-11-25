package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class L_60_排列序列 {

    public static void main(String[] args) {

        String permutation = getPermutation(3, 3);

        System.out.println(permutation);
    }

    static public String getPermutation(int n, int k) {


        LinkedList<Integer> list = new LinkedList<>();

        List<String> rest = new ArrayList<>();


        boolean[] visited = new boolean[n + 1];




        dp(n, k, list, rest, visited);


        return rest.get(k - 1);
    }

    static void dp(int n, int k, LinkedList<Integer> list, List<String> rest, boolean[] visited) {
        if (list.size() == n) {
            rest.add(list.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }


        for (int i = 1; i <= n; i++) {
            if (visited[i] == true)
                continue;
            list.add(i);
            visited[i] = true;
            dp(n, k, list, rest, visited);
            list.removeLast();
            visited[i] = false;
        }

    }
}
