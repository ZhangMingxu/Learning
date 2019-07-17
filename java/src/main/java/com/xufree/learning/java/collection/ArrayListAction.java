package com.xufree.learning.java.collection;

import java.util.ArrayList;

/**
 * @author zhangmingxu ON 11:33 2019-07-17
 **/
public class ArrayListAction {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(5);
    }
}
