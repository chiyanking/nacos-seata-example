package cn.wangtk.algorithm;

public class L_58_最后一个单词的长度 {

    public static void main(String[] args) {

        String s = "Hello World";
        int last = lengthOfLastWord(s);
    }

    static public int lengthOfLastWord(String s) {
        String[] s1 = s.split(" ");


        if (s1.length > 0) {
            return s1[s1.length - 1].length();
        }

        return -1;
    }

}
