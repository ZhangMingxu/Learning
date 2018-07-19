package com.xufree.learning.java.thread;

import com.xufree.learning.util.SleepUtil;

public class Daemon {
    /**
     * 验证守护线程的finally语句块不一定被执行
     */
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                SleepUtil.second(10);
            } finally {
                System.out.println("Hello Word!");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
