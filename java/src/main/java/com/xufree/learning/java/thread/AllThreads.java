package com.xufree.learning.java.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 获取所有线程
 *
 * @author zhangmingxu ON 10:41 2019-08-21
 **/
public class AllThreads {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup root = getRoot();
        Thread[] threads = new Thread[root.activeCount()];
        root.enumerate(threads);
        for (Thread thread : threads) {
            System.out.println(thread.getName() + "---" + thread.isDaemon());
        }
        new CountDownLatch(1).await();
    }

    private static ThreadGroup getRoot() {
        ThreadGroup cur = Thread.currentThread().getThreadGroup();
        ThreadGroup root = cur;
        while (true) {
            cur = cur.getParent();
            if (cur == null) {
                return root;
            }
            root = cur;
        }
    }
}
