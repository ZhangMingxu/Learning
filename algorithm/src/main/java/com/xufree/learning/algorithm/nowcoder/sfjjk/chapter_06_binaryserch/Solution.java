package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_06_binaryserch;

/**
 * Created by 张明旭 on 2017/6/21.
 */
public class Solution {
    public int getLessIndex(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }

        if (arr[n - 1] < arr[n - 2]) {
            return n - 1;
        }
        int left = 1;
        int right = n - 2;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid - 1] & arr[mid] < arr[mid + 1]) {
                return mid;
            } else if (arr[mid] < arr[mid - 1] & arr[mid] > arr[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 9, 2, 1, 4, 0, 10, 9, 0, 8, 3, 5, 6, 7, 1, 9, 2, 4, 0, 7};
        new Solution().getLessIndex(arr);
    }
}
