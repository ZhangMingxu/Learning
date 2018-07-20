package com.xufree.learning.java.thread;

import com.xufree.learning.common.util.SleepUtil;

public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void begin() {
        TIME_THREADLOCAL.set(now());
    }

    public static long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    private static Long now() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        Profiler.begin();
        SleepUtil.second(2);
        System.out.println("Cost:"+Profiler.end());
    }
}
