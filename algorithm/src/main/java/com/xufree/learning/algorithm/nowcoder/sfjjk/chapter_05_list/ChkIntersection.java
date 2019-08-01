package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

/**
 * Created by 张明旭 on 2017/6/20.
 * 如何判断两个有环单链表是否相交？相交的话返回第一个相交的节点，不想交的话返回空。如果两个链表长度分别为N和M，
 * 请做到时间复杂度O(N+M)，额外空间复杂度O(1)。
 * <p>
 * 给定两个链表的头结点head1和head2(注意，另外两个参数adjust0和adjust1用于调整数据,与本题求解无关)。
 * 请返回一个bool值代表它们是否相交。
 */
public class ChkIntersection {
    public boolean chkInter(ListNode head1, ListNode head2, int adjust0, int adjust1) {
        // write code here
        ListNode p1 = find(head1);
        ListNode p2 = find(head2);
        if(p1 == p2) return true;
        ListNode cur = p1.next;
        while(p1!=cur){
            if(cur == p2) return true;
            cur = cur.next;
        }
        return false;
    }

    public ListNode find(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null & fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
