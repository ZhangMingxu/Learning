package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/6.
 */
public class CountingSort {
    public int[] countingSort(int[] A, int n) {
        // write code here
        if (A==null&n<2){
            return A;
        }else {
            int max = A[0];
            //找到数组最大值
            for (int i = 1; i < n; i++) {
                if (A[i]>max){
                    max = A[i];
                }
            }
            //用一个数组当做桶来存数据
            int[] buket = new int[max+1];
            //buket[i]上的数代表 i 在待排数组出现的次数
            for (int i = 0; i < n; i++) {
                buket[A[i]]+=1;
            }
            int index = 0;
            for (int i = 0; i < buket.length; i++) {
                if (buket[i]!=0){
                    for (int j = 0; j <buket[i] ; j++) {
                        A[index++]=i;
                    }
                }else {
                    continue;
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,5,2,3};
        new CountingSort().countingSort(A,A.length);
        System.out.print("");
    }
}
