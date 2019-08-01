package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_03_string;

/**
 * Created by 张明旭 on 2017/6/10.
 */
public class Translation {
    public String stringTranslation(String A, int n, int len) {
        char[] chars = A.toCharArray();
        reverse(chars,0,len-1);
        reverse(chars,len,n-1);
        reverse(chars,0,n-1);
        return new String(chars);
    }
    private void reverse(char[] chars,int begin,int end){
        for (int i = 0; i < (end-begin)/2+1; i++) {
            char c = chars[begin+i];
            chars[begin+i] = chars[end-i];
            chars[end-i] = c;

        }
    }

    public static void main(String[] args) {
        String s = "ABCDE";
        System.out.print(new Translation().stringTranslation(s,s.length(),3));
    }
}
