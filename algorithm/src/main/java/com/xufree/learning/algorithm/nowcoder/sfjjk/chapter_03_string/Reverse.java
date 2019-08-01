package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_03_string;

/**
 * Created by 张明旭 on 2017/6/10.
 */
public class Reverse {
    public String reverseSentence(String A, int n) {
        String[] strings = A.split(" ");
        StringBuffer s = new StringBuffer();

        for (int i = 0; i < strings.length/2; i++) {
            String tem = strings[i];
            strings[i] = strings[strings.length-i-1];
            strings[strings.length-i-1] = tem;
        }
        String result = "";
        for (int i = 0; i < strings.length; i++) {
            if (i!=strings.length-1){
                result+=strings[i]+" ";
            }else {
                result+=strings[i];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String s = "dog loves pig";
        System.out.print(new Reverse().reverseSentence(s,s.length()));
    }
}
