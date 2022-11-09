package cn.wangtk.algorithm;


public class l_28_找出字符串中第一个匹配项的下标 {


    public static void main(String[] args) {

        int start = strStr("leetcode", "leeto");
        System.out.println(start);
    }

    static public int strStr(String haystack, String needle) {

        if (needle.length() > haystack.length()) {
            return -1;
        }

        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == needle.length()) {
                    return i - j;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return -1;
    }
}