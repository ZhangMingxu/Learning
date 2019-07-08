package com.xufree.learning.java.test;

import com.xufree.learning.common.util.SleepUtil;
import com.xufree.learning.java.concurrent.ShareLock;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    @Test
    public void testLock() {
        Lock lock = new ReentrantLock();
        lock.tryLock();
        try {
            System.out.println("a");
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void testSharedLock() {
        final ShareLock shareLock = new ShareLock(5);
        class Worker extends Thread {

            @Override
            public void run() {
//                if (shareLock.tryLock()){
//                    try {
//                        System.out.println(Thread.currentThread().getName()+"\tget lock");
//                        SleepUtil.second(12);
//                    }finally {
//                        shareLock.unlock();
//                    }
//                }else {
//                    System.out.println(Thread.currentThread().getName()+"\tdo not get lock");
//
//                }
                while (true) {
                    shareLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "\tget lock");
                        SleepUtil.second(1);
                    } finally {
                        shareLock.unlock();
                    }
                }
            }
        }
        for (int i = 0; i < 20; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        System.out.println("在等待队列里面的线程:");
        shareLock.getQueuedThreads().forEach(System.out::println);




    }
}
