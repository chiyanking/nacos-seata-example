package cn.wangtk.algorithm;

import java.util.HashMap;
import java.util.Map;

public class 最小覆盖子串 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String result = new 最小覆盖子串().minWindow("AA", "AA");
        System.out.println(result);
        result = new 最小覆盖子串().minWindow("ADOBECODEBANC", "ABC");
        System.out.println(result);

    }

    public String minWindow(String s, String t) {

        Map<Character, Integer> need = new HashMap<Character, Integer>(), have = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            Integer value = need.get(t.charAt(i));
            need.put(t.charAt(i), value == null ? 1 : value + 1);
        }
        int left = 0, right = 0, start = 0, len = Integer.MAX_VALUE;
        int valid = 0;

        while (right < s.length()) {
            Character tmp = s.charAt(right);
            if (need.get(tmp) != null) {
                Integer value = have.get(tmp);
                have.put(tmp, value == null ? 1 : value + 1);
                if (Integer.compare(have.get(tmp), need.get(tmp)) == 0 ) {
                    valid++;
                }
            }
            right++;
            while (Integer.compare(valid, need.size()) == 0) {
                if (len > right - left) {
                    start = left;
                    len = right - left;
                }
                Character tr = s.charAt(left);
                if (need.get(tr) != null) {
                    if (Integer.compare(need.get(tr), have.get(tr)) == 0) {
                        valid--;
                    }
                    Integer val = have.get(tr);
                    if (val != null) {
                        have.put(tr, val - 1);
                    }
                }
                left++;
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

}
