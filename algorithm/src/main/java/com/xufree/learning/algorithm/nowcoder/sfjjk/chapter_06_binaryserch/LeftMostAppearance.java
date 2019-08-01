package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_06_binaryserch;

/**
 * Created by 张明旭 on 2017/6/21.
 * 对于一个有序数组arr，再给定一个整数num，请在arr中找到num这个数出现的最左边的位置。
 * <p>
 * 给定一个数组arr及它的大小n，同时给定num。请返回所求位置。若该元素在数组中未出现，请返回-1。
 * <p>
 * 测试样例：
 * [1,2,3,3,4],5,3
 * 返回：2
 */
public class LeftMostAppearance {
    public int findPos(int[] arr, int n, int num) {
        // write code here
        if (n==0){
            return -1;
        }
        if (n==1){
            if (arr[0]==num){
                return 0;
            }else {
                return -1;
            }
        }
        int left = 0;
        int right = n-1;
        int res = -1;
        while(left<=right){
            int mid = left+(right-left)/2;
            if (arr[mid]==num){
                right=mid-1;
                res=mid;
            }else if (arr[mid]<num){
                left=mid+1;
            }else {
                right=mid-1;
            }

        }
        return res;

    }
}
