package com.xufree.learning.algorithm.nowcoder.jianzhioffer;


/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author zhangmingxu ON 20:48 2019-04-08
 **/
public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int first = array[0];
        for (int i : array) {
            if (i<first){
                return i;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        int[] array = {3,4,5,1,2};
        MinNumberInRotateArray sultion = new MinNumberInRotateArray();
        System.out.println(sultion.minNumberInRotateArray(array));
    }
}
