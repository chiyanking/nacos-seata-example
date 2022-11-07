package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 字符排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cs = sc.nextLine();
        String[] chr = cs.split(" ");
        String row = chr[0];
        Integer index = Integer.parseInt(chr[1]);

        List<Character> up = new ArrayList<>();
        List<Character> down = new ArrayList<>();
        for (int i = 0; i < row.length(); i++) {
            Character c = row.charAt(i);
            if (Character.isUpperCase(c)) {
                up.add(c);
            } else {
                down.add(c);
            }
        }
        up.sort((a1, a2) -> a1 - a2);
        down.sort((a1, a2) -> a1 - a2);
        up.addAll(down);
    

        String res = up.stream().map(i -> i + "").collect(Collectors.joining());
        Character in = res.charAt(index-1);
        for (int i = 0; i < row.length(); i++) {
            Character c = row.charAt(i);
            if(c == in ){
                System.out.println(i);
                break;
            }
        }
    }

}
