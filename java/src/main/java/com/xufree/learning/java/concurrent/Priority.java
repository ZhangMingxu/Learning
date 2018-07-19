package com.xufree.learning.java.concurrent;

import com.xufree.learning.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证Java线程优先级在什么系统上可以起作用
 * Windows上可以起作用。
 * Job Priority:1  job count:403866
 * Job Priority:1  job count:403774
 * Job Priority:1  job count:403719
 * Job Priority:1  job count:403784
 * Job Priority:1  job count:403889
 * Job Priority:10  job count:3172450
 * Job Priority:10  job count:3167109
 * Job Priority:10  job count:3171571
 * Job Priority:10  job count:3174370
 * Job Priority:10  job count:3166459
 */
public class Priority {
    private static volatile boolean noStart = true;
    private static volatile boolean noEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread-" + i);
            thread.setPriority(priority);
            thread.start();
        }
        noStart = false;
        SleepUtil.second(10);
        noEnd = false;
        for (Job job : jobs) {
            System.out.println("Job Priority:" + job.priority + "  job count:" + job.count);
        }
    }

    static class Job implements Runnable {
        private int priority;
        private int count;

        Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (noStart) {
                Thread.yield();
            }
            while (noEnd) {
                Thread.yield();
                count++;
            }
        }
    }
}
