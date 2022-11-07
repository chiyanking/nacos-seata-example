package cn.wangtk.algorithm;

public class 最长回文 {
    public static void main(String[] args) {
        
        new 最长回文().longestPalindrome("cbbd");
    }
    
    public String longestPalindrome(String s) {
        if (s.length() == 1){
            return s;
        }
        String maxLength="";
        for(int i =0;i<s.length();i++){
           String s1= longStr(s,i,i);
           if(maxLength.length() < s1.length()){
               maxLength = s1;
           }
           String s2 =  longStr(s,i,i+1);
           if(maxLength.length()<s2.length()){
               maxLength = s2;
           }
        }
        return maxLength;
    }
    public String longStr(String s,int left,int right){
        while(left>=0&&right<s.length()){
            if(s.charAt(left)==s.charAt(right)){
                left--;
                right++;
            }else{
                break;
            }
        }
        return s.substring(left+1, right);
    }
}
