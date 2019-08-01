package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;


/**
 * Created by 张明旭 on 2017/6/3.
 */
public class MergeSort {
    public int[] mergeSort(int[] A, int n) {
        // write code here
        sort(A, 0, n - 1);
        return A;
    }

    /**
     * 将索引从left到right范围的数组元素进行归并排序
     *
     * @param data  待排序的数组
     * @param left  待排序的数组的第一个元素索引
     * @param right 待排序的数组的最后一个元素的索引
     *              先把所有数据通过递归，分割好数据，分割成两两一组
     */
    private void sort(int[] data, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            sort(data, left, center);
            sort(data, center + 1, right);
            merge(data, left, center, right);
        } else {
            return;
        }
    }

    /**
     * 合并
     * 将两个数组进行归并，归并前两个数组已经有序，归并后依然有序。
     *
     * @param data   数组对象
     * @param left   左数组的第一个元素的索引
     * @param center 左数组的最后一个元素的索引，center+1是右数组第一个元素的索引
     * @param right  右数组的最后一个元素的索引
     */
    private void merge(int[] data, int left, int center, int right) {
        int firstBegin = left;          //要合并第一个数组的开头
        int firstCur = firstBegin;      //要合并第一个数组的指针
        int firstEnd = center;          //要合并第一个数组的结尾
        int secondBegin = center + 1;     //要合并第二个数组的开头
        int secondCur = secondBegin;    //要合并第二个数组的指针
        int secondEnd = right;          //要合并第二个数组的结尾
        int[] temArray = new int[data.length];  //临时数组存放排序后的结果
        int i = 0;                      //临时数组指针
        //合并数组到临时数组中
        while (firstCur <= firstEnd & secondCur <= secondEnd) {
            if (data[firstCur] < data[secondCur]) {
                temArray[i++] = data[firstCur++];
            } else {
                temArray[i++] = data[secondCur++];
            }
        }
        while (firstCur <= firstEnd) {
            temArray[i++] = data[firstCur++];
        }
        while (secondCur <= secondEnd) {
            temArray[i++] = data[secondCur++];
        }
        for (int j = 0; j < i; j++) {
            data[left + j] = temArray[j];
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 2, 3};
        new MergeSort().mergeSort(A, A.length);
        System.out.println();
    }
}
