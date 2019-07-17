package com.xufree.learning.java.collection;

import java.util.LinkedList;

/**
 * @author zhangmingxu ON 13:54 2019-07-17
 **/
public class LinkedListAction {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        linkedList.remove(5);
    }
}
