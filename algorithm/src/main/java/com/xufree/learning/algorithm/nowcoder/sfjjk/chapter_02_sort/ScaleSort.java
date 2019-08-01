package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/6.
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。
 * 请选择一个合适的排序算法针对这个数据进行排序。
 * <p>
 * 给定一个int数组A，同时给定A的大小n和题意中的k，请返回排序后的数组。
 * <p>
 * 测试样例：
 * [2,1,4,3,6,5,8,7,10,9],10,2]
 * 返回：[1,2,3,4,5,6,7,8,9,10]
 */
public class ScaleSort {
    public int[] sortElement(int[] A, int n, int k) {
        // write code here
        for (int i = 0; i < n; i++) {
            if (i+k<n){
                buildMinHeap(A,i,i+k-1);
            }else {
                buildMinHeap(A,i,n-1);
            }
        }

        return A;
    }
    public void buildMinHeap(int[] data,int begin,int end){
        int parentIndex = (end-1)/2+begin;
        for (int i = parentIndex; i >=begin ; i--) {
            int curentIndex = i;
            while ((curentIndex * 2 + 1)-begin<=end){
                int minIndex = (curentIndex * 2 + 1)-begin;
                if (minIndex+1<=end){
                    if (data[minIndex]>data[minIndex+1]){
                        minIndex = minIndex+1;
                    }
                }
                if (data[minIndex]<data[curentIndex]){
                    swap(data,curentIndex,minIndex);
                    curentIndex = minIndex;
                }else {
                    break;
                }
            }

        }

    }
    public void swap(int[] data,int i,int j){
        int temp = data[i];
        data[i]=data[j];
        data[j]=temp;
    }

    public static void main(String[] args) {
        int[] A = {2,1,4,3,6,5,8,7,10,9};
        new ScaleSort().sortElement(A,10,2);
        System.out.print("");;
    }
}
