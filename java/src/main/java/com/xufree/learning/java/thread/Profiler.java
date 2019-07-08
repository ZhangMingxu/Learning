package com.xufree.learning.java.thread;

import com.xufree.learning.common.util.SleepUtil;


/**
 * 利用ThreadLocal实现统计运行时间
 */
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = ThreadLocal.withInitial(System::currentTimeMillis);

    public static void begin() {
        TIME_THREAD_LOCAL.set(now());
    }

    public static long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }

    private static Long now() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Profiler.begin();
        SleepUtil.second(2);
        System.out.println("Cost:" + Profiler.end());
    }
}
