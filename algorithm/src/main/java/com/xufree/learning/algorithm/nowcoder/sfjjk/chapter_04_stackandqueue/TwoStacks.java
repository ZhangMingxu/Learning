package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_04_stackandqueue;


import java.util.ArrayList;


/**
 * Created by 张明旭 on 2017/6/12.
 */
public class TwoStacks {
    public ArrayList<Integer> twoStacksSort(int[] numbers) {
        int[] help = new int[numbers.length];
        int n = numbers.length;
        int i = 0, j = n;//栈的当前指针
        int cur;
        while (i < n) {
            //++为出栈，--为进栈
            //弹出栈中的第一个元素，下移index
            cur = numbers[i++];

            if (j == n) {
                // 将弹出元素压入help栈顶
                help[--j] = cur;
            } else if (cur <= help[j]) {
                // 将弹出元素压入help栈顶
                help[--j] = cur;
            } else if (cur > help[j]) {
                // 弹出help栈中的元素，并将其压入到主栈栈顶，直到cur小于等于help栈顶元素
                while (j < n && cur > help[j]) {
                    numbers[--i] = help[j++];
                }
                // 将弹出元素压入help栈顶
                help[--j] = cur;
            }

        }
        numbers[0] = help[n - 1];
        ArrayList<Integer> list = new ArrayList<>();
        for (int m = 0; m < n; m++) {
            list.add(help[n - m - 1]);
        }
        return list;
    }
}
