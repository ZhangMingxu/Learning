package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_03_string;

/**
 * Created by 张明旭 on 2017/6/10.
 * 对于一个给定的字符串数组，请找到一种拼接顺序，使所有小字符串拼接成的大字符串是所有可能的拼接中字典序最小的。
 * <p>
 * 给定一个字符串数组strs，同时给定它的大小，请返回拼接成的串。
 * <p>
 * 测试样例：
 * ["abc","de"],2
 * "abcde"
 */
public class Prior {
    public String findSmallest(String[] strs, int n) {
        // write code here
        sort(strs,0,n-1);
        String result ="";
        for (int i = 0; i < n; i++) {
            result+=strs[i];
        }

        return result;
    }
    private void sort(String[] strings,int begin,int end){
        int i = begin;
        int j = end;
        boolean flag = true;
        if (i>=j){
            return;
        }
        while (i!=j){
            if ((strings[j]+strings[i]).compareTo(strings[i]+strings[j])<0){
                swap(strings,i,j);
                flag=!flag;
            }
            if (flag){
                i++;
            }else {
                j--;
            }
        }
        i++;
        j--;
        sort(strings,begin,j);
        sort(strings,i,end);
    }
    private void swap(String[] strings,int i,int j){
        if (i==j){
            return;
        }else {
            String temp = strings[i];
            strings[i]= strings[j];
            strings[j]=temp;
        }
    }

    public static void main(String[] args) {
        String[] strings = {"de","abc"};
        System.out.print(new Prior().findSmallest(strings,strings.length));
    }
}
