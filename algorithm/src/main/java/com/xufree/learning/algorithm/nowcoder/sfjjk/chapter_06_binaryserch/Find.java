package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_06_binaryserch;

public class Find {
    public int findPos(int[] arr, int n) {
        // write code here
        int result = -1;
        if (arr[0]>n-1||arr[n-1]<0){
            return result;
        }
        int left = 0;
        int right = n-1;
        while (left<=right){
            int mid = left+((right-left)/2);
            if (arr[mid]>mid){
                right = mid-1;
            }else if (arr[mid]<mid){
                left=mid+1;
            }else {
                result = mid;
                right = mid-1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {0,5,8,9};
        System.out.print(new Find().findPos(a,a.length));
    }
}
