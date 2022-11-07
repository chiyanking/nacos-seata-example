package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 热点网站统计 {

    public static void main(String[] args) {
        HashMap<String, Integer> urlC = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        PriorityQueue<String> p = new PriorityQueue<>((i1, i2) -> {
            String[] s1 = i1.split(":");
            String[] s2 = i2.split(":");
            if (s1[0] == s2[0]) {
                return 0;
            }
            return s2[0].compareTo(s1[0]);
        });
        HashMap<String, Integer> map = new HashMap<>();
        List<String> l = new ArrayList<>();

        while (true) {

            String line = scanner.nextLine();
            if (line.contains(".")) {
                urlC.put(line, urlC.getOrDefault(line, 0) + 1);

                String pre = new StringBuilder().append(urlC.get(line) - 1).append(":").append(line).toString();
                if (map.get(pre) != null) {
                    p.remove(pre);
                    map.remove(pre);
                }
                String cur = new StringBuilder().append(urlC.get(line)).append(":").append(line).toString();
                if (p.size() > 10) {
                    String[] p1 = p.peek().split(":");
                    if (Integer.parseInt(p1[0]) < urlC.get(line)) {
                        p.poll();
                        p.add(cur);
                        map.put(cur, urlC.get(line));
                    }
                } else {
                    p.add(cur);
                    map.put(cur, urlC.get(line));
                }

            } else if (line.isEmpty()) {
                for (int i = 0; i < l.size(); i++) {
                    System.out.println(l.get(i));
                }
                break;
            } else {
                int max = Integer.parseInt(line);
                List<String> l2 = p.stream().sorted((i1, i2) -> {
                    String[] s1 = i1.split(":");
                    String[] s2 = i2.split(":");
                    if (s1[0] == s2[0]) {
                        return 0;
                    }
                    return s2[0].compareTo(s1[0]);
                }).map(it -> it.split(":")[1]).collect(Collectors.toList());
                l.add(l2.subList(0, max).stream().collect(Collectors.joining(",")));
            }

        }

    }

}
