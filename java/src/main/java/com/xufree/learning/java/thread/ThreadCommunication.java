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
    private static volatile int num;

    public static void main(String[] args) throws InterruptedException {
        useObject();
    }

    private static void useCondition() throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition flag = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                flag.await(); //等待通知
                System.out.println(Thread.currentThread().getName() + " await over num is " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();


        new Thread(() -> {
            lock.lock();
            try {
                flag.await(); //等待通知
                System.out.println(Thread.currentThread().getName() + " await over num is " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("start signal 2");
                num = 2;
                flag.signalAll();//发送通知
            } finally {
                lock.unlock();
            }
        }).start();
        System.out.println("main sum is " + num);

//        new Thread(() -> {
//            lock.lock();
//            try {
//                System.out.println("start signal 3");
//                num = 3;
//                flag.signal();//发送通知
//            } finally {
//                lock.unlock();
//            }
//        }).start();
    }

    /**
     * 等待通知经典范例
     * syncharonized(object){
     * while(flag){
     * object.wait();
     * }
     * doSomthing();
     * }
     * syncharonized(object){
     * 改变条件
     * object.notifyAll();
     * }
     */
    private static void useObject() {
        class Flag {
            private boolean flag = false;
        }

        Flag flag = new Flag();
        Object lock = new Object();
        new Thread(() -> {
            try {
                synchronized (lock) {
                    System.out.println("start wait");
                    while (!flag.flag) {
                        lock.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + " wait over num is " + num);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("notify num 2");
                num = 2;
                flag.flag = true;
                lock.notify();

            }
        }).start();


    }
}
