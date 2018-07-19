package com.xufree.learning.java.thread;

import com.xufree.learning.util.FormatUtil;
import com.xufree.learning.util.SleepUtil;

import java.util.Date;

public class WaitNotify {
    private static boolean flag = true;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true.wait at:" + FormatUtil.formatTimeFromDate(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false.running at:" + FormatUtil.formatTimeFromDate(new Date()));

            }
        }, "WaitThread").start();
        SleepUtil.second(1);
        new Thread(() -> {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock.notify at:" + FormatUtil.formatTimeFromDate(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtil.second(5);
            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again.sleep at:" + FormatUtil.formatTimeFromDate(new Date()));
                SleepUtil.second(5);
            }
        }, "NotifyThread").start();
    }
}
