package com.xufree.learning.algorithm;

/**
 * 奇数放左边，偶数放右边
 *
 * @author zhangmingxu ON 11:23 2019-07-11
 **/
public class OddAndEven {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] result = process(array);
        assert result != null;
        for (int i : result) {
            System.out.println(i);
        }
    }

    private static int[] process(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int[] result = new int[array.length];
        int left = 0;
        int right = array.length - 1;
        for (int i : array) {
            if ((i % 2) == 0) { //偶数
                result[right--] = i;
            } else {//奇数
                result[left++] = i;
            }
        }
        return result;
    }
}
