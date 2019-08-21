package com.xufree.learning.java.memory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 实验GC
 *
 * @author zhangmingxu ON 11:06 2019-08-21
 **/
public class MemoryInAction {
    private static final List<BigDecimal> holder = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            try {
                useMemory();
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void useMemory() {
        for (int i = 0; i < 10000; i++) {
//            holder.add(new BigDecimal("5646545"));
            new BigDecimal("5646545");
        }
        System.out.println("size: " + holder.size());
    }
}
