package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_02_sort;

/**
 * Created by 张明旭 on 2017/6/4.
 */
public class HeapSort {
    public int[] heapSort(int[] A, int n) {
        // write code here
        for (int i = 0; i < n - 1; i++) {
            int lastIndex = n - 1 - i;
            //创创建堆
            buildMaxHeap(A, lastIndex);
            //交换堆顶元素和最后一个元素
            swap(A, 0, lastIndex);
        }
        return A;
    }

    /**
     * 创建大顶堆
     *
     * @param data      需要的数组
     * @param lastIndex 需要创建大顶堆最后一个元素的索引
     */
    private void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        //(lastIndex - 1)/2    为最后一个节点的父节点的索引(除根节点)
        //都是从子节点开始判断  k的值分别是 5 3 1 索引号依次判断
        int parentIndex = (lastIndex - 1) / 2;
        for (int i = parentIndex; i >= 0; i--) {
            //保存正在判断的节点
            int curIndex = i;
            //找到该父节点的子节点((curIndex*2+1 , (curIndex*2+2)
            while (curIndex * 2 + 1 <= lastIndex) {
                //左右孩子中较大节点的下标 初始化为左孩子
                int bigIndex = curIndex * 2 + 1;
                if (bigIndex < lastIndex) {
                    //如果右孩子更大 把右孩子下标付给bigIndex
                    if (data[bigIndex] < data[bigIndex + 1]) {
                        bigIndex++;
                    }
                }
                //如果父节点比两个子节点最大值小就交换
                if (data[curIndex] < data[bigIndex]) {
                    swap(data, curIndex, bigIndex);
                    curIndex = bigIndex;
                } else {
                    break;
                }
            }
        }

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
        int[] A = {14, 313, 206, 53, 182, 215, 169, 148, 158, 272, 22, 80, 176, 200, 266, 301, 37, 234, 174, 163, 158, 158, 112, 281, 86, 14, 306, 137, 258, 27, 247, 33, 102, 5, 217, 254, 286, 255, 195, 234, 229, 225, 296, 158, 247, 42, 124, 96, 292, 71, 303, 144, 57, 297, 315, 44, 20, 111, 68, 92, 181, 187, 130, 167, 59, 156, 240, 75, 118, 184, 46, 94, 74, 142};
        new HeapSort().heapSort(A, A.length);
        for (int i : A) {
            System.out.print(i + " ");
        }
    }
}
