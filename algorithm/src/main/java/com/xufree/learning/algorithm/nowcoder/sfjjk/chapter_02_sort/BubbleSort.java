package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/3.
 */
public class BubbleSort {
    public int[] bubbleSort(int[] A, int n) {
        // write code here
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 2, 3};
        new BubbleSort().bubbleSort(A, A.length);
        System.out.println();
    }
}
