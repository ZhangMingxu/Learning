package com.xufree.learning.java.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通讯测试
 *
 * @author zhangmingxu ON 16:53 2019-07-05
 **/
public class ThreadCommunication {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition flag = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                flag.await(); //等待通知
                System.out.println("await over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
//            lock.lock();
            try {
                System.out.println("start signal");
                flag.signal();//发送通知
            } finally {
//                lock.unlock();
            }
        }).start();
    }
}
