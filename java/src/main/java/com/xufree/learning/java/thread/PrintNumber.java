package com.xufree.learning.java.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangmingxu ON 16:43 2019-07-11
 **/
public class PrintNumber {
    public static void main(String[] args) throws InterruptedException {
//        printA();
        printB();
    }

    /**
     * 3个线程，打印分别有自己的数组，内含不同元素，多线程分别按顺序交替轮流打印3个数组中的元素
     * [A1,A2,A3]
     * [B1,B2,B3]
     * [C1,C2,C3]
     * <p>
     * 输出：
     * A1,B1,C2,A2,B2,C2,A3,B3,C3
     */
    private static void printA() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch count = new CountDownLatch(3);
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        new Thread(() -> {
            String[] array = {"A1", "A2", "A3"};
            int i = 0;
            while (true) {
                if (i == array.length) {
                    break;
                }
                lock.lock();
                try {
                    a.await();
                    System.out.println(array[i++]);
                    b.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
            count.countDown();
        }, "A").start();
        new Thread(() -> {
            String[] array = {"B1", "B2", "B3","B4"};
            int i = 0;
            while (true) {
                if (i == array.length) {
                    break;
                }
                lock.lock();
                try {
                    b.await();
                    System.out.println(array[i++]);
                    c.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
            count.countDown();
        }, "B").start();
        new Thread(() -> {
            String[] array = {"C1", "C2", "C3"};
            int i = 0;
            while (true) {
                if (i == array.length) {
                    break;
                }
                lock.lock();
                try {
                    c.await();
                    System.out.println(array[i++]);
                    a.signal();
                } catch (InterruptedException ignored) {

                } finally {
                    lock.unlock();
                }
            }
            count.countDown();
        }, "C").start();
        lock.lock();
        try {
            a.signal();
        } finally {
            lock.unlock();
        }
        count.await();
    }

    /**
     * 三个线程，打印同一个数组，按顺序轮流
     */
    private static void printB() throws InterruptedException {
        int[] array = new int[500];
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch count = new CountDownLatch(3);
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        final AtomicInteger i = new AtomicInteger(0);

        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        a.await();
                        if (i.get() < array.length) {
                            System.out.println("A" + array[i.getAndIncrement()]);
                            b.signal();
                        } else {
                            b.signal();
                            break;
                        }
                    } catch (InterruptedException ignored) {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                count.countDown();
            }

        }, "A").start();
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        b.await();
                        if (i.get() < array.length) {
                            System.out.println("B" + array[i.getAndIncrement()]);
                            c.signal();
                        } else {
                            c.signal();
                            break;
                        }
                    } catch (InterruptedException ignored) {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                count.countDown();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                while (true) {
                    lock.lock();
                    try {
                        c.await();
                        if (i.get() < array.length) {
                            System.out.println("C" + array[i.getAndIncrement()]);
                            a.signal();
                        } else {
                            a.signal();
                            break;
                        }
                    } catch (InterruptedException ignored) {

                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                count.countDown();
            }
        }, "C").start();
        lock.lock();
        try {
            a.signal();
        } finally {
            lock.unlock();
        }
        count.await();
    }
}
