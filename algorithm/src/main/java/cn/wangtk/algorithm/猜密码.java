package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 猜密码 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String passwordLine = scan.nextLine();

        String[] password = passwordLine.split(",");
        Boolean[] visited = new Boolean[password.length];

        int k = scan.nextInt();

        List<String> list = new ArrayList<>();
        List<String> res = new ArrayList<>();
        dp(password, visited, 0, k, list, res);

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

    }

    static void dp(String[] password, Boolean[] visited, int cur, int minLength, List<String> list, List<String> res) {

        if (list.size() >= minLength) {
            res.add(list.stream().collect(Collectors.joining(",")));
        }
        for (int i = cur; i < password.length; i++) {
            if (visited[i] != null)
                continue;
            visited[i] = true;
            list.add(password[i]);
            dp(password, visited, i, minLength, list, res);
            visited[i] = null;
            list.remove(password[i]);
        }

    }

}