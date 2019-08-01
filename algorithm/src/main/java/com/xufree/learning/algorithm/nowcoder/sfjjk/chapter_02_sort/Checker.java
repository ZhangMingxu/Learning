package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/6.
 * 请设计一个高效算法，判断数组中是否有重复值。必须保证额外空间复杂度为O(1)。
 * <p>
 * 给定一个int数组A及它的大小n，请返回它是否有重复值。
 * <p>
 * 测试样例：
 * [1,2,3,4,5,5,6],7
 * 返回：true
 */
public class Checker {
    public boolean checkDuplicate(int[] a, int n) {
        // write code here
        boolean result = false;
        shellSort(a, n);
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i - 1]) {
                return true;
            }
        }
        return result;
    }

    private void shellSort(int[] data, int n) {
        int step = n / 2;
        while (step > 0) {
            for (int i = step; i < n; i++) {
                for (int j = i; j >= step; j -= step) {
                    if (data[j] < data[j - step]) {
                        swap(data, j, j - step);
                    }
                }
            }
            step /= 2;
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void main(String[] args) {
        int[] A = {2, 4, 2, 1, 3, 1, 5};
        new Checker().checkDuplicate(A, A.length);

    }
}
