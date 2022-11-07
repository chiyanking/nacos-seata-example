package cn.wangtk.algorithm;

import java.util.ArrayList;
import java.util.List;

public class 回文数 {

    public static void main(String[] args) {
        boolean palindrome = isPalindrome(101);
        System.out.println(palindrome);

    }

    static public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        List<Integer> rest = new ArrayList<>();
        while (x != 0) {
            rest.add(x % 10);
            x = x / 10;
        }
        int l = 0, r = rest.size() - 1;

        while (r > l) {
            if (rest.get(r) != rest.get(l)) {
                return false;
            }
            r--;
            l++;
        }
        return true;
    }

}