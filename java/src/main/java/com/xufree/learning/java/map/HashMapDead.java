package com.xufree.learning.java.map;

import java.util.HashMap;
import java.util.UUID;

/**
 * 测试HashMap死循环
 *
 * @author zhangmingxu ON 17:48 2019-05-27
 **/
public class HashMapDead {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(2);
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");
        t.start();
        t.join();
        System.out.println(map.size());
    }
}
