package com.wangtk.mybatis.algorithm;

public class LongestPalindrome {


    public static void main(String[] args) {

        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String babad = longestPalindrome.longestPalindrome("bbadfasdfasdfasdfdddfdasfadsfasdfadsfrrqwer");
        System.out.println(babad);
        int pwwkew = longestPalindrome.lengthOfLongestSubstring("abcabcbb");
        System.out.println(pwwkew);
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

}
