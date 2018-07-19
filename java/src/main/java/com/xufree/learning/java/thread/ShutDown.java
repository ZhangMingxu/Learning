package com.xufree.learning.java.thread;

import com.xufree.learning.util.SleepUtil;

public class ShutDown {
    public static void main(String[] args) {
        Runner one = new Runner();
        Thread thread = new Thread(one, "Runner-1");
        thread.start();
        SleepUtil.second(1);
        thread.interrupt();
        Runner two = new Runner();
        thread = new Thread(two, "Runner-2");
        thread.start();
        SleepUtil.second(1);
        two.cancel();
        SleepUtil.second(1);

    }

    static class Runner implements Runnable {
        private boolean on = true;
        private long count;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "  count:" + count);
        }

        void cancel() {
            on = false;
        }
    }
}
