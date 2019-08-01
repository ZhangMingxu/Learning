package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/3.
 */
public class SelectionSort {
    public int[] selectionSort(int[] A, int n) {
        // write code here
        for (int i = 0; i < n - 1; i++) {
            int min = A[i];
            int indexOfMin = i;
            //下面的for循环是为了找到后面k-i个数中最小值 并记录最小值下标
            for (int j = i + 1; j < n; j++) {
                if (A[j] < min) {
                    min = A[j];
                    indexOfMin = j;
                }
            }
            //第i个值和后面的 k-i 个数中的最小值交换
            int temp = A[i];
            A[i] = A[indexOfMin];
            A[indexOfMin] = temp;

        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 2, 3};
        new SelectionSort().selectionSort(A, A.length);
        System.out.println();
    }
}
