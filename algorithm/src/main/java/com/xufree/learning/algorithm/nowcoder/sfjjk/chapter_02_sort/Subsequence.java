package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/8.
 * 对于一个数组，请设计一个高效算法计算需要排序的最短子数组的长度。
 * <p>
 * 给定一个int数组A和数组的大小n，请返回一个二元组，代表所求序列的长度。(原序列位置从0开始标号,若原序列有序，返回0)。保证A中元素均为正整数。
 * <p>
 * 测试样例：
 * [1,4,6,5,9,10],6
 * 返回：2
 */
public class Subsequence {
    public int shortestSubsequence(int[] A, int n) {
        // write code here
        int max = A[0];
        int min = A[n - 1];
        int right = 0;
        int left = 0;
        for (int i = 1; i < n; i++) {
            if (max < A[i]) {
                max = A[i];
            } else if (max > A[i]) {
                right = i;
            } else {
                continue;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (min > A[i]) {
                min = A[i];

            } else if (min < A[i]) {
                left = i;
            } else {
                continue;
            }
        }
        return right - left == 0 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        int[] A = {1, 4, 6, 5, 9, 10};
        new Subsequence().shortestSubsequence(A, A.length);
    }

}
