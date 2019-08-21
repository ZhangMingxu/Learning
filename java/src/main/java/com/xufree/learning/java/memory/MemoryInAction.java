package com.xufree.learning.java.memory;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * 实验GC
 *
 * @author zhangmingxu ON 11:06 2019-08-21
 **/
public class MemoryInAction {

    public static void main(String[] args) {
        while (true) {
            try {
                useMemory();
                TimeUnit.MICROSECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void useMemory() {
        for (int i = 0; i < 10000; i++) {
            new BigDecimal("5646545");
        }
    }
}
