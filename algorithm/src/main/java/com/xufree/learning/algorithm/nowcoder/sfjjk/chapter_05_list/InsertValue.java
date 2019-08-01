package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

import java.util.List;

/**
 * Created by 张明旭 on 2017/6/13.
 * 有一个整数val，如何在节点值有序的环形链表中插入一个节点值为val的节点，并且保证这个环形单链表依然有序。
 * <p>
 * 给定链表的信息，及元素的值A及对应的nxt指向的元素编号同时给定val，请构造出这个环形链表，并插入该值。
 * <p>
 * 测试样例：
 * [1,3,4,5,7],[1,2,3,4,0],2
 * 返回：{1,2,3,4,5,7}
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class InsertValue {
    public ListNode insert(int[] A, int[] nxt, int val) {
        // write code here
        //处理原链表为空的情况
        if (A==null||A.length==0){
            ListNode head = new ListNode(val);
            head.next=head;
            return head;
        //处理插入的节点比所有节点都小的情况
        }else if(A[0]>=val) {
            ListNode head = new ListNode(val);
            ListNode cur = head;
            for (int i = 0; i < A.length; i++) {
                ListNode newNode = new ListNode(A[i]);
                cur.next=newNode;
                cur = newNode;
            }
            cur.next=head;
            return head;
        //处理插入的节点比所有节点都大的情况
        }else if (val>=A[A.length-1]){
            ListNode head = new ListNode(A[0]);
            ListNode cur = head;
            for (int i = 1; i < A.length; i++) {
                ListNode newNode = new ListNode(A[i]);
                cur.next=newNode;
                cur = newNode;
            }
            cur.next = new ListNode(val);
            cur.next.next=head;
            return head;
        }else {
            ListNode head = new ListNode(A[0]);
            ListNode cur = head;
            for (int i = 1; i < A.length; i++) {
                if (val>=A[i-1]&val<=A[i]){
                    ListNode newNode = new ListNode(val);
                    newNode.next = new ListNode(A[i]);
                    cur.next = newNode;
                    cur = newNode.next;
                    val = -1;
                }else {
                    ListNode newNode = new ListNode(A[i]);
                    cur.next=newNode;
                    cur = newNode;
                }
            }
            cur.next = head;
            return head;
        }
    }

    public static void main(String[] args) {
        int[] A = {4,6,10,14};
        int[] net = {1,2,3,0};
        int val = 10;
        ListNode head = new InsertValue().insert(A,net,val);
        ListNode cur = head;
        while(cur.next!=head){
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.print(cur.val+" ");
    }
}
