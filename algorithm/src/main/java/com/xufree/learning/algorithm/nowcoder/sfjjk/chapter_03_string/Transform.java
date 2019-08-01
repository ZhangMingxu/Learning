package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_03_string;

/**
 * Created by 张明旭 on 2017/6/10.
 * 对于两个字符串A和B，如果A和B中出现的字符种类相同且每种字符出现的次数相同，则A和B互为变形词，请设计一个高效算法，
 * 检查两给定串是否互为变形词。
 * <p>
 * 给定两个字符串A和B及他们的长度，请返回一个bool值，代表他们是否互为变形词。
 * <p>
 * 测试样例：
 * "abc",3,"bca",3
 * 返回：true
 */
public class Transform {
    public boolean chkTransform(String A, int lena, String B, int lenb) {
        // write code here
        if (lena!=lenb){
            return false;
        }else {
            int[] a = new int[65536];
            int[] b = new int[65536];
            for (int i = 0; i < lena; i++) {
                a[A.charAt(i)] += 1;
                b[B.charAt(i)] += 1;
            }
            for (int i = 0; i < a.length; i++) {
                if (a[i]!=b[i]){
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        String A = "abc";
        String B = "bac";
        System.out.print(new Transform().chkTransform(A,A.length(),B,B.length()));
    }
}
