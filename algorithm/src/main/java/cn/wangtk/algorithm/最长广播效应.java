package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 最长广播效应 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] parseLine = scan.nextLine().split(" ");
        int num = Integer.parseInt(parseLine[0]);

        int length = Integer.parseInt(parseLine[1]);

        HashMap<Integer, Boolean> visited = new HashMap<>();

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < length; i++) {
            String line = scan.nextLine();
            String[] fromTo = line.split(" ");
            int from = Integer.parseInt(fromTo[0]);
            int to = Integer.parseInt(fromTo[1]);

            List<Integer> list = map.getOrDefault(from, new ArrayList<>());
            list.add(to);
            map.put(from, list);

            list = map.getOrDefault(to, new ArrayList<>());
            list.add(from);
            map.put(to, list);

        }
        int from = Integer.parseInt(scan.nextLine());
        // int sum = dp(map, visited, from, 0);
        int depth = 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(from);
        visited.put(from, true);
        while (!queue.isEmpty()) {

            int len = queue.size();
            Boolean hasDepath = false;
            for (int i = 0; i < len; i++) {
                int val = queue.poll();
                List<Integer> adjList = map.get(val);
                for (int j = 0; j < adjList.size(); j++) {
                    int adj = adjList.get(j);
                    if (visited.get(adj) == null) {
                        queue.add(adj);
                        visited.put(adj, true);
                        hasDepath = true;
                    }
                }
            }
            if (hasDepath) {
                depth++;
            }
        }

        System.out.println(depth * 2);
    }

    // static int dp(HashMap<Integer, List<Integer>> map, HashMap<Integer, Boolean>
    // visited, int cur, int depth) {

    // if (visited.get(cur) != null) {
    // return 0;
    // }
    // visited.put(cur, true);
    // int sum = 0;
    // List<Integer> list = map.get(cur);
    // if (list != null) {
    // for (int i = 0; i < list.size(); i++) {

    // sum += dp(map, visited, list.get(i), depth + 1);
    // }
    // }

    // return sum + depth * 2;
    // }
}
