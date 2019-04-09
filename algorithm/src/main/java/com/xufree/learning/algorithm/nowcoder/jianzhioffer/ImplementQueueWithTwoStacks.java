package com.xufree.learning.algorithm.nowcoder.jianzhioffer;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * @author zhangmingxu ON 18:41 2019-03-29
 **/
public class ImplementQueueWithTwoStacks {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    //每次push完stack2肯定是空的
    public void push(int node) {
        if (!stack2.empty()) {
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
        }
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        ImplementQueueWithTwoStacks sulotion = new ImplementQueueWithTwoStacks();
        sulotion.push(1);
        sulotion.push(2);
        sulotion.push(3);
        sulotion.push(4);
        for (int i = 0; i < 4; i++) {
            System.out.println(sulotion.pop());
        }
    }
}
