package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/3.
 */
public class InsertionSort {
    public int[] insertionSort(int[] A, int n) {
        // write code here
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (A[j - 1] > A[j]) {
                    int temp = A[j - 1];
                    A[j - 1] = A[j];
                    A[j] = temp;
                } else {
                    break;
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = {54, 35, 48, 36, 27, 12, 44, 44, 8, 14, 26, 17, 28};
        new InsertionSort().insertionSort(A, A.length);
        System.out.println();
    }
}
