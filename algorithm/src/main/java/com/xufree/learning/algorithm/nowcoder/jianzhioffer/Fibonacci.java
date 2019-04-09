package com.xufree.learning.algorithm.nowcoder.jianzhioffer;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 *
 * @author zhangmingxu ON 20:59 2019-04-08
 **/
public class Fibonacci {

    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci sultion = new Fibonacci();
        System.out.println(sultion.fibonacci(39));
    }
}
