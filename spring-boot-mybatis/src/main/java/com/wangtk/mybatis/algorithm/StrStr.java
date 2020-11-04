package com.wangtk.mybatis.algorithm;

//      实现 strStr() 函数。
//
//        给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
//
//        示例 1:
//
//        输入: haystack = "hello", needle = "ll"
//        输出: 2
//        示例 2:
//
//        输入: haystack = "aaaaa", needle = "bba"
//        输出: -1
//        说明:
//
//        当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
//        对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
public class StrStr {
    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        int i = strStr.strStr("a", "a");
        System.out.println(i);
    }


    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int p = 0;
        int i = 0;
        for (; i < haystack.length(); i++) {
            if (p == needle.length()) {
                return i - p;
            }
            if (haystack.charAt(i) == needle.charAt(p)) {
                p++;
            } else {
                i = i - p;
                p = 0;
            }
        }
        if (p == needle.length()) {//判断完成
            return i - needle.length();
        }
        return -1;
    }


    /**
     * lllc
     * llc
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (needle.length() > haystack.length())
            return -1;
        int j = 0;//needle指针
        int i = 0;
        for (i = 0; i < haystack.length(); ++i) {
            if (j == needle.length()) {//判断完成
                return i - needle.length();
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            } else {
                i = i - j;
                j = 0;
            }
        }
        if (j == needle.length()) {//判断完成
            return i - needle.length();
        }
        return -1;
    }
}
