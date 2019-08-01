package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

/**
 * Created by 张明旭 on 2017/6/19.
 * 现在有一个单链表。链表中每个节点保存一个整数，再给定一个值val，把所有等于val的节点删掉。
 * <p>
 * 给定一个单链表的头结点head，同时给定一个值val，请返回清除后的链表的头结点，保证链表中有不等于该值的其它值。请保证其他元素的相对顺序。
 * <p>
 * 测试样例：
 * {1,2,3,4,3,2,1},2
 * {1,3,4,3,1}
 */
public class ClearValue {
    public ListNode clear(ListNode head, int val) {
        // write code here
        ListNode newHead = null;
        ListNode cur = head;
        ListNode newCur = newHead;
        while (cur != null) {
            if (cur.val != val) {
                if (newHead == null) {
                    newHead = new ListNode(cur.val);
                    newCur = newHead;
                } else {
                    newCur.next = new ListNode(cur.val);
                    newCur = newCur.next;
                }
            }
            cur = cur.next;
        }
        return newHead;
    }
}
