package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_04_stackandqueue;

import java.util.Stack;

/**
 * Created by 张明旭 on 2017/6/12.
 */
public class Solution {
    Stack<Integer> stackData = new Stack<>();
    Stack<Integer> stackMin = new Stack<>();

    public void push(int node) {
        stackData.push(node);
        if (stackMin.isEmpty()) {
            stackMin.push(node);
        } else {
            if (node < stackMin.peek()) {
                stackMin.push(node);
            } else {
                stackMin.push(stackMin.peek());
            }
        }
    }

    public void pop() {
        stackData.pop();
        stackMin.pop();
    }

    public int top() {
        return stackData.peek();
    }

    public int min() {
        return stackMin.peek();

    }
}
