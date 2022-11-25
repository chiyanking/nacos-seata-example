package cn.wangtk.algorithm;

import java.util.*;

public class L_207_课程表 {

    public static void main(String[] args) {


        boolean b = canFinish(5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}});


        System.out.println(b);


    }

    static public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (prerequisites.length == 0) {
            return true;
        }
        HashMap<Integer, Set<Integer>> gh = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            Integer degree = inDegree.getOrDefault(prerequisite[0], 0);
            degree++;
            inDegree.put(prerequisite[0], degree);
            degree = inDegree.getOrDefault(prerequisite[1], 0);
            inDegree.put(prerequisite[1], degree);


            Set<Integer> orDefault = gh.getOrDefault(prerequisite[1], new HashSet<>());
            orDefault.add(prerequisite[0]);
            gh.put(prerequisite[1], orDefault);
        }

        Queue<Integer> queue = new ArrayDeque<>(numCourses);


        inDegree.forEach((key, value) -> {
            if (value == 0) {
                queue.add(key);
            }
        });


        int count = 0;
        while (!queue.isEmpty()) {

            Integer poll = queue.poll();
            count++;

            Set<Integer> outs = gh.getOrDefault(poll, new HashSet<>());

            outs.forEach(it -> {

                Integer in = inDegree.get(it);
                in--;
                inDegree.put(it, in);

                if (in == 0) {
                    queue.add(it);
                }
            });


        }

        return count == numCourses;

    }
}
