package com.xufree.learning.java.thread;

import java.util.HashMap;

/**
 * @author zhangmingxu ON 17:01 2019-02-13
 * 测试HashMap死锁问题 理论上jdk7有死锁 现在用jdk8测试
 * 并没有问题
 **/
public class HashMapEndlessLoop {
    private static HashMap<Long, Object> map = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> map.put(System.nanoTime(), new Object())).start();
        }
    }
}
