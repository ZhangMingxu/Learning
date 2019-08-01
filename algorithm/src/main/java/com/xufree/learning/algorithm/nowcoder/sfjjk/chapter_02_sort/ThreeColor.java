package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/8.
 */
public class ThreeColor {
    public int[] sortThreeColor(int[] A, int n) {
        // write code here
        int indexOf0 = -1;
        int indexOf2 = n;
        for (int i = 0; i < indexOf2; i++) {
            if (A[i] == 0) {
                swap(A, i, ++indexOf0);
            } else if (A[i] == 2) {
                swap(A, i--, --indexOf2);
            }
        }
        return A;
    }

    private void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        } else {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] A = {0,1,1,0,2,2};
        new ThreeColor().sortThreeColor(A,A.length);
        System.out.print("");
    }
}
