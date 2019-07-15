package com.xufree.learning.algorithm;

/**
 * 一个二维矩阵，从左到右是升序，从上到下是降序，找一个数是否存在于矩阵中
 *
 * @author zhangmingxu ON 16:07 2019-07-15
 **/
public class TwoDimensionSearch {
    public static void main(String[] args) {
        int[][] array = {
                {7, 8, 9},
                {4, 5, 6},
                {1, 2, 3}
        };
        int value = 6;
        if (search(array, value)) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }

    private static boolean search(int[][] array, int value) {
        int i = 0;
        int j = 0;
        int temp = array[i][j];
        while (true) {
            if (value == temp) {
                return true;
            } else if (value > temp && j < array[0].length - 1) {//向右
                temp = array[i][++j];
            } else if (value < temp && i < array.length - 1) {//向下
                temp = array[++i][j];
            } else {
                return false;
            }
        }
    }
}
