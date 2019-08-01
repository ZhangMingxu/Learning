package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

import java.util.Stack;

/**
 * Created by 张明旭 on 2017/6/19.
 * 请编写一个函数，检查链表是否为回文。
 * <p>
 * 给定一个链表ListNode* pHead，请返回一个bool，代表链表是否为回文。
 * <p>
 * 测试样例：
 * {1,2,3,2,1}
 * 返回：true
 * {1,2,3,2,3}
 * 返回：false
 */
public class Palindrome {
    public boolean isPalindrome(ListNode pHead) {
        // write code here
        Stack<Integer> stack = new Stack<>();
        ListNode cur = pHead;
        while(cur!=null){
            stack.push(cur.val);
            cur = cur.next;
        }
        while(pHead!=null){
            if(pHead.val!=stack.pop()){
                return false;
            }
            pHead = pHead.next;
        }
        return true;
    }

}
