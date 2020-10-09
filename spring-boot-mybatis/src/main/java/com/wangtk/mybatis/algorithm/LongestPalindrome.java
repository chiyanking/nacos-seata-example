package com.wangtk.mybatis.algorithm;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindrome {


    public static void main(String[] args) {

        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String babad = longestPalindrome.longestPalindrome("bbadfasdfasdfasdfdddfdasfadsfasdfadsfrrqwer");
        System.out.println(babad);
        int pwwkew = longestPalindrome.lengthOfLongestSubstring("abcabcbb");
        System.out.println(pwwkew);

        boolean palindrome = longestPalindrome.isPalindrome(0);


    }

    /***
     * "abcabcbb"
     *
     * @param s
     * @return
     */

    public String longestPalindrome(String s) {
        String maxLength = "";
        for (int i = 0; i < s.length(); i++) {
            String length1 = isSame(i, i, s);
            String length2 = isSame(i, i + 1, s);
            if (length1.length() > maxLength.length()) {
                maxLength = length1;
            }
            if (length2.length() > maxLength.length()) {
                maxLength = length2;
            }
        }
        return maxLength;
    }

    public String isSame(int a, int b, String s) {
        while (a >= 0 && b < s.length()) {
            if (s.charAt(a) != s.charAt(b)) {
                return s.substring(a + 1, b);
            }
            a--;
            b++;
        }
        return s.substring(a + 1, b);
    }


    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        String hasString = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (hasString.contains(c + "")) {
                hasString = hasString.substring(1);
            }
            hasString = hasString.concat(c + "");
            if (maxLength < hasString.length()) {
                maxLength = hasString.length();
            }
        }
        return maxLength;
    }


    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        while (x != 0) {
            int left = x % 10;
            x = x / 10;
            list.add(left);
        }
        boolean isevn = list.size() % 2 == 0;
        int mid = list.size() / 2;
        if (!isevn) {
            return isPalindrome(mid, mid, list);
        } else {
            return isPalindrome(mid - 1, mid, list);
        }


    }

    boolean isPalindrome(int left, int right, List<Integer> list) {

        while (left >= 0 && right < list.size()) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left--;
            right++;
        }
        return true;
    }

    /**
     * s = "aabc"
     * p = ".*b*"
     * aabbc
     * .*b*
     * a*b*
     * c*b*
     * <p>
     * <p>
     * <p>
     * abvnsdfkjqwer
     * a*b
     * a*ab
     * <p>
     * aaaaaaaab
     * a*b
     * a*ab
     * <p>
     * <p>
     * abvnsdfkjqwer
     * .*
     * <p>
     * <p>
     * <p>
     * aaabc
     * b.a*
     * aaabca
     * a*b.a*
     * <p>
     * <p>
     * 3
     * aaabcaaa
     * ab.a*
     */

    public boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        boolean isFirstMath = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (isFirstMath && isMatch(s.substring(1), p));
        } else {
            return isFirstMath && isMatch(s.substring(1), p.substring(1));
        }

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) {
            return strs[0];
        }
        int i = 0;
        String str = "";
        String head = strs[0];
        while (i < head.length()) {
            for (String s : strs) {
                if (i > s.length() - 1 || head.charAt(i) != s.charAt(i)) {
                    return str;
                }
            }
            str += head.charAt(i);
            i++;
        }
        return str;
    }


    public int romanToInt(String s) {
        char[] list = s.toCharArray();
        int i = 0;
        int sum = 0;


        while (i < list.length) {
            if (list[i] == 'M') {
                sum += 1000;
                i++;
                continue;
            }
            if (list[i] == 'C' && i + 1 < list.length && list[i + 1] == 'M') {
                sum += 900;
                i = i + 2;
                continue;
            }
            if (list[i] == 'D') {
                sum += 500;
                i++;
                continue;
            }
            if (list[i] == 'C' && i + 1 < list.length && list[i + 1] == 'D') {
                sum += 400;
                i = i + 2;
                continue;
            }
            if (list[i] == 'C') {
                sum += 100;
                i++;
                continue;
            }
            if (list[i] == 'X' && i + 1 < list.length && list[i + 1] == 'C') {
                sum += 90;
                i = i + 2;
                continue;
            }
            if (list[i] == 'L') {
                sum += 50;
                i++;
                continue;
            }
            if (list[i] == 'X' && i + 1 < list.length && list[i + 1] == 'L') {
                sum += 40;
                i = i + 2;
                continue;
            }
            if (list[i] == 'X') {
                sum += 10;
                i++;
                continue;
            }
            if (list[i] == 'I' && i + 1 < list.length && list[i + 1] == 'X') {
                sum += 9;
                i = i + 2;
                continue;
            }
            if (list[i] == 'V') {
                sum += 5;
                i++;
                continue;
            }
            if (list[i] == 'I' && i + 1 < list.length && list[i + 1] == 'V') {
                sum += 4;
                i = i + 2;
                continue;
            }
            if (list[i] == 'I') {
                sum += 1;
                i++;
                continue;
            }
        }
        return sum;
    }

}
