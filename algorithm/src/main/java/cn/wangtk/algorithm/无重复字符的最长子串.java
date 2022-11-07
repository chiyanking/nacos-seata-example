package cn.wangtk.algorithm;

import java.util.HashMap;

public class 无重复字符的最长子串 {
    public static void main(String[] args) {

        System.out.println(new 无重复字符的最长子串().lengthOfLongestSubstring("ab"));
        System.out.println(new 无重复字符的最长子串().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new 无重复字符的最长子串().lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        HashMap<Character, Integer> windows = new HashMap<>();

        int r = 0;
        int l = 0;
        int max = 1;
        while (r < s.length()) {
            Character chr = s.charAt(r);
            windows.put(chr, windows.getOrDefault(chr, 0) + 1);
            r++;
            while (windows.get(chr) > 1) {
                Character curL = s.charAt(l);
                if (curL == chr) {
                    windows.put(curL, windows.get(curL) - 1);
                } else {
                    windows.remove(curL);
                }
                l++;
            }
            if (windows.size() > max) {
                max = windows.size();
            }
        }
        return max;

    }

}
