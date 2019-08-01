package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

/**
 * Created by 张明旭 on 2017/6/13.
 * 实现一个算法，删除单向链表中间的某个结点，假定你只能访问该结点。
 * <p>
 * 给定带删除的节点，请执行删除操作，若该节点为尾节点，返回false，否则返回true
 */

public class Remove {
    public boolean removeNode(ListNode pNode) {
        // write code here
        if (pNode.next==null){
            return false;
        }else {
            pNode.val=pNode.next.val;
            pNode.next=pNode.next.next;
            return true;
        }
    }
}
