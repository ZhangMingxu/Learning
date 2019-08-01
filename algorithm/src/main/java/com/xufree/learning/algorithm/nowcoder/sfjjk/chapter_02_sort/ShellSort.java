package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/4.
 */
public class ShellSort {
    public int[] shellSort(int[] A, int n) {
        //设置可变增量 变化规则为 increment = increment*3+1;
        int increment = 1;
        while (increment < n / 3) {
            increment = increment * 3 + 1;
        }
        //开始缩小增量的插入排序
        while (increment > 0) {
            for (int i = increment; i < n; i++) {
                for (int j = i; j >= increment; j -= increment) {
                    if (A[j] < A[j - increment]) {
                        int temp = A[j];
                        A[j] = A[j - increment];
                        A[j - increment] = temp;
                    }else {
                        break;
                    }
                }
            }
            increment = (increment - 1) / 3;
        }
        return A;
    }

    public static void main(String[] args) {
        int[] A = {54,35,48,36,27,12,44,44,8,14,26,17,28};
        new ShellSort().shellSort(A, A.length);

        System.out.print("");
    }
}
