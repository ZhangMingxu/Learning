package com.xufree.learning.algorithm.nowcoder.jianzhioffer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * @author zhangmingxu ON 20:45 2019-04-09
 **/
public class JumpFloorII {
    public int jumpFloorII(int target) {
        if (target == 0){
            return 1;
        }
        int result = 1;
        for (int i = 0; i < target-1; i++) {
            result*=2;
        }
        return result;
    }

    public static void main(String[] args) {
        JumpFloorII solution = new JumpFloorII();
        System.out.println(solution.jumpFloorII(5));
    }
}
