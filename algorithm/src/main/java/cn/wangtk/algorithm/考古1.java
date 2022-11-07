package cn.wangtk.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class 考古1 {
    static char[][] arr = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());
        String[] chars = sc.nextLine().split(" ");
        Boolean[] visited = new Boolean[num + 1];
        Set<String> stack = new HashSet<>();

        Arrays.fill(visited, false);
        Arrays.sort(chars);

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            dp(chars, list, visited, stack);
        }
        for (String st : stack) {
            System.out.println(st);
        }
    }

    static void dp(String[] chars, LinkedList<Integer> list, Boolean[] visited, Set<String> stack) {

        if (list.size() == chars.length) {
            stack.add(list.stream().map(it -> chars[it]).collect(Collectors.joining()));
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            list.add(i);
            dp(chars, list, visited, stack);
            visited[i] = false;
            list.removeLast();
        }

    }

}
