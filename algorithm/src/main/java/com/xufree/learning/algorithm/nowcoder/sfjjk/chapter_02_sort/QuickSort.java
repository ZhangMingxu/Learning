package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/3.
 */
public class QuickSort {
    public int[] quickSort(int[] A, int n) {
        // write code here
        sort(A, 0, n - 1);
        return A;
    }

    private void sort(int[] data, int begin, int end) {
        int left = begin;     //左索引
        int right = end;      //右索引
        if (left >= right) {     //判断是否到中间了 如果到中间了就返回
            return;
        }
        boolean flag = true; //标记是从左到右(true) 还是从右到左(false)
        while (left != right) {
            if (data[left] > data[right]) {
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                flag = !flag;
            }
            if (flag) {
                left++;
            } else {
                right--;
            }
        }
        left++;
        right--;
        sort(data, begin, right);
        sort(data, left, end);
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 2, 3};
        new QuickSort().quickSort(A, A.length);
        System.out.println();
    }
}
