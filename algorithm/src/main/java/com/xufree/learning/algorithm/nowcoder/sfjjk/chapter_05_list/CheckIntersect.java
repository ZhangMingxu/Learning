package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

/**
 * Created by 张明旭 on 2017/6/20.
 * 现在有两个无环单链表，若两个链表的长度分别为m和n，请设计一个时间复杂度为O(n + m)，额外空间复杂度为O(1)的算法，判断这两个链表是否相交。
 * <p>
 * 给定两个链表的头结点headA和headB，请返回一个bool值，代表这两个链表是否相交。保证两个链表长度小于等于500。
 */
public class CheckIntersect {
    public boolean chkIntersect(ListNode headA, ListNode headB) {
        // write code here
        int Alength = 0;
        int Blength = 0;
        ListNode Ahead = headA;
        ListNode Bhead = headB;
        while(headA!=null){
            Alength++;
            headA=headA.next;
        }
        while(headB!=null){
            Blength++;
            headB=headB.next;
        }
        if (Alength>Blength){
            for (int i = 0; i < Blength; i++) {
                Bhead=Bhead.next;
            }
        }else {
            for (int i = 0; i < Alength; i++) {
                Ahead = Ahead.next;
            }
        }
        while(Ahead!=null&Bhead!=null){
            if (Ahead==Bhead){
                break;
            }
            Ahead = Ahead.next;
            Bhead = Bhead.next;
        }
        if (Ahead==Bhead){
            return true;
        }
        return false;
    }
}
