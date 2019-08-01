package com.xufree.learning.algorithm.nowcoder.sfjjk.chapter_05_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张明旭 on 2017/6/13.
 * 现有两个升序链表，且链表中均无重复元素。请设计一个高效的算法，打印两个链表的公共值部分。
 * <p>
 * 给定两个链表的头指针headA和headB，请返回一个vector，元素为两个链表的公共部分。请保证返回数组的升序。
 * 两个链表的元素个数均小于等于500。保证一定有公共值
 * <p>
 * 测试样例：
 * {1,2,3,4,5,6,7},{2,4,6,8,10}
 * 返回：[2.4.6]
 */
public class Common {
    public int[] findCommonParts(ListNode headA, ListNode headB) {
        // write code here
        List<Integer> list = new ArrayList<>();
        while (headA != null && headB != null) {
            if (headA.val < headB.val) {
                headA = headA.next;
            } else if (headA.val > headB.val) {
                headB = headB.next;
            } else {
                list.add(headA.val);
                headA = headA.next;
                headB = headB.next;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
