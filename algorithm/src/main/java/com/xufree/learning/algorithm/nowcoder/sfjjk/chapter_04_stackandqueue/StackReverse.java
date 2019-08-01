package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_04_stackandqueue;

import java.util.Stack;

/**
 * Created by 张明旭 on 2017/6/12.
 */
public class StackReverse {
    public int[] reverseStack(int[] A, int n) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(A[i]);
        }
        reverse(stack);
        int index = 0;
        while (!stack.isEmpty()) {
            A[index++] = stack.pop();
        }
        return A;
    }

    private int get(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = get(stack);
            stack.push(result);
            return last;
        }
    }

    private void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        } else {
            int i = get(stack);
            reverse(stack);
            stack.push(i);
        }
    }

    public static void main(String[] args) {
        int[] A = {126,399,224,272,230};
        new StackReverse().reverseStack(A,A.length);
    }
}
