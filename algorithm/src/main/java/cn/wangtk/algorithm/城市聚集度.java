package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class 城市聚集度 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());

        HashMap<Integer, List<Integer>> gragh = new HashMap<>();

        String twoValue = sc.nextLine();
        while (twoValue != null && twoValue.length() > 0) {
            String[] twoA = twoValue.split(" ");
            int start = Integer.parseInt(twoA[0]);
            int end = Integer.parseInt(twoA[1]);
            List<Integer> values = gragh.getOrDefault(start, new ArrayList());
            values.add(end);
            gragh.put(start, values);

            values = gragh.getOrDefault(end, new ArrayList());
            values.add(start);
            gragh.put(end, values);
            twoValue = sc.nextLine();
        }
        HashMap<Integer, Boolean> visited = new HashMap<>();
        HashMap<Integer, Integer> target_max = new HashMap<>();
        for (int target : gragh.keySet()) {
            for (int cur : gragh.keySet()) {
                target_max.put(target, Math.max(target_max.getOrDefault(target, 1), dp(gragh, visited, cur, target)));
            }
            visited = new HashMap<>();
        }
        int min = Integer.MAX_VALUE;
        int min_key = 0;
        for (Entry<Integer, Integer> en : target_max.entrySet()) {
            if (en.getValue() < min) {
                min = en.getValue();
                min_key = en.getKey();
            }
        }
        System.out.println(min_key);
        System.out.println(min);
    }

    static int dp(HashMap<Integer, List<Integer>> gragh, HashMap<Integer, Boolean> visited, int cur, int target) {
        if (Integer.compare(cur, target) == 0) {
            return 0;
        }
        if (visited.get(cur) != null) {
            return 0;
        }
        visited.put(cur, true);
        List<Integer> curList = gragh.get(cur);
        int max = 1;
        for (int i = 0; i < curList.size(); i++) {
            max = max + dp(gragh, visited, curList.get(i), target);
        }
        return max;

    }

}