package com.xufree.learning.java.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 如何使用非volatile关键字实现volatile功能
 * 自增操作依旧无法保证线程安全
 *
 * @author zhangmingxu ON 15:55 2019-05-20
 **/
public class Volatile {
    private long aLong = 0L;                          //64位的long型普通变量

    private synchronized void set(long l) {    //普通变量加锁写
        aLong = l;
    }

    private void increment(long i) {           //普通方法调用
        long temp = get();                    //调用已同步的读方法
        temp += i;                           //普通写操作
        set(temp); //调用已同步的写方法
    }

    private synchronized long get() {          //已同步的读方法
        return aLong;
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile v = new Volatile();
        CountDownLatch count = new CountDownLatch(2);
        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                v.increment(1);
            }
            count.countDown();
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                v.increment(-1);
            }
            count.countDown();
        }).start();
        count.await();
        System.out.println(v.aLong);
    }
}
