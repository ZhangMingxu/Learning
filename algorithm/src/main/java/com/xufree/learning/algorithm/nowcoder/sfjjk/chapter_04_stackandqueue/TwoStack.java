package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_04_stackandqueue;

import java.util.Stack;

/**
 * Created by 张明旭 on 2017/6/12.
 * 编写一个类,只能用两个栈结构实现队列,支持队列的基本操作(push，pop)。
 * <p>
 * 给定一个操作序列ope及它的长度n，其中元素为正数代表push操作，为0代表pop操作，
 * 保证操作序列合法且一定含pop操作，请返回pop的结果序列。
 * <p>
 * 测试样例：
 * [1,2,3,0,4,0],6
 * 返回：[1,2]
 */
public class TwoStack {
    Stack<Integer> stackPush = new Stack<>();
    Stack<Integer> stackPop = new Stack<>();

    public int[] twoStack(int[] ope, int n) {
        int length = 0;
        for (int i = 0; i < n; i++) {
            if (ope[i] == 0) {
                length++;
            }
        }
        int[] result = new int[length];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (ope[i] > 0) {
                push(ope[i]);
            } else {
                result[index++] = pop();
            }
        }
        return result;
    }

    private void push(int value) {
        stackPush.push(value);
    }

    private int pop() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public static void main(String[] args) {
        int[] A = {498,0,54,325,0,598,339,288,68,176,0,483,133,261,246,0,613,18,23,299,0,379,516,246,0,0,0,0,195,0,0,0,226,0,0,0,615,256,0,215,472,0,610,162,274,550,141,0,0,547,505,0,0,191,0,0,198,55,292,158,11,248,113,0,338,383,466,620,240,6,0,0,260,0,452,150,419,590,227,323,575,0,0,343,523,0,32,437,0,618,242,0,221,483,116,131,0,479,375,186,94,435,388,98,80,556,202,151,197,103,301,242,466,366,329,350,360,302,351,0,501,517,0,343,0,298,327,307,356,173,198,0,0,0,169,76,411,350,330,85,0,275,563,0,139,0,386,2,371,0,430,575,170,529,607,0,87,180,10,446,339,611,0,388,182,231,74,460,458,0,340,513,0,0,47,4,408,431,495,0,0,0,0,326,343,0,0,615,155,0,98,384,229,560,0,614,0,627,596,80,0,515,88,223,378,536,535,459,0,312,279,199,337,344,608,66,455,27,0,171,473,474,308,63,0,503,0,387,279,494,542,495,253,467,177,0,474,243,54,559,0,414,328,53,568,418,0,242,213,177,353,0};
        new TwoStack().twoStack(A,A.length);
    }
}
