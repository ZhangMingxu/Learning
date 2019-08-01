package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

import java.util.Stack;

/**
 * Created by 张明旭 on 2017/6/19.
 * 有一个单链表，请设计一个算法，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。
 * 例如链表1->2->3->4->5->6->7->8->null，K=3这个例子。调整后为，3->2->1->6->5->4->7->8->null。因为K==3，所以每三个节点之间逆序，
 * 但其中的7，8不调整，因为只有两个节点不够一组。
 * <p>
 * 给定一个单链表的头指针head,同时给定K值，返回逆序后的链表的头指针。
 */
public class KInverse {
    public ListNode inverse(ListNode head, int k) {
        // write code here
        if (head == null || k < 2) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        int num = 0;
        while (cur != null) {
            num++;
            cur = cur.next;
        }
        int cishu = num / k;
        cur = head;
        ListNode newHead = null;
        ListNode newCur = newHead;
        for (int i = 0; i < cishu; i++) {
            for (int j = 0; j < k; j++) {
                stack.push(cur);
                cur = cur.next;
            }
            for (int j = 0; j < k; j++) {
                if (newHead == null) {
                    newHead = new ListNode(stack.pop().val);
                    newCur = newHead;
                } else {
                    newCur.next = new ListNode(stack.pop().val);
                    newCur = newCur.next;
                }
            }
        }
        newCur.next = cur;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        new KInverse().inverse(head, 2);
    }
}
