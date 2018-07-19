package com.xufree.learning.util;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
    public static void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
