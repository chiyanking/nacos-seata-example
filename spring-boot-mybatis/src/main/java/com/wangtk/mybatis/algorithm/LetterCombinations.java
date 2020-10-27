package com.wangtk.mybatis.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    public static void main(String[] args) {

        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> strings = letterCombinations.letterCombinations("23");
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if(digits==null||digits.length()<1){
            return list;

        }
        String[] s = new String[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            switch (digits.charAt(i)){
                case '2':s[i]="abc";break;
                case '3':s[i]="def";break;
                case '4':s[i]="ghi";break;
                case '5':s[i]="jkl";break;
                case '6':s[i]="mno";break;
                case '7':s[i]="pqrs";break;
                case '8':s[i]="tuv";break;
                case '9':s[i]="wxyz";break;
            }
        }
        getList(0, s, "", list);
        return list;
    }

    public void getList(int i, String[] s, String upStr, List<String> list) {
        if (i == s.length - 1) {
            for (int j = 0; j < s[i].length(); j++) {
                list.add(upStr + s[i].charAt(j));
            }
            return;
        }
        for (int j = 0; j < s[i].length(); j++) {
            getList(i+1, s, upStr + s[i].charAt(j), list);
        }
    }
}
