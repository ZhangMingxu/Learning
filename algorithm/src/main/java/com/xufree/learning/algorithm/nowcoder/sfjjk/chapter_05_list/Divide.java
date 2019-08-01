package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

/**
 * Created by 张明旭 on 2017/6/13.
 */
public class Divide {
    public ListNode listDivide(ListNode head, int val) {
        // write code here
        ListNode minHead = null;
        ListNode minCur = minHead;
        ListNode maxHead = null;
        ListNode maxCur = null;
        while(head!=null){
            if (head.val<=val){
                if (minHead == null){
                    minHead = new ListNode(head.val);
                    minCur = minHead;
                }else {
                    ListNode newNode = new ListNode(head.val);
                    minCur.next = newNode;
                    minCur = newNode;
                }
            }else {
                if (maxHead == null){
                    maxHead = new ListNode(head.val);
                    maxCur = maxHead;
                }else {
                    ListNode newNode = new ListNode(head.val);
                    maxCur.next = newNode;
                    maxCur = newNode;
                }
            }
            head = head.next;
        }
        minCur.next=maxHead;
        return minHead;
    }

    public static void main(String[] args) {
        int[] num = {1620,1134,861,563,1};
        ListNode head = null;
        ListNode cur = null;
        for (int i = 0; i < num.length; i++) {
            if (head==null){
                head = new ListNode(num[i]);
                cur = head;
            }else {
                ListNode newNode = new ListNode(num[i]);
                cur.next = newNode;
                cur = newNode;
            }
        }
        head = new Divide().listDivide(head,1134);
        while (head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
    }
}
