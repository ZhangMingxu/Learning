package com.xufree.learning.annotation.test;

import com.xufree.learning.common.annotation.TimeStatistics;
import org.junit.Test;

public class TimeStatisticsTest {
    @TimeStatistics
    private void m() {
        int count = 0;
        for (int i = 0; i < 10000000; i++) {
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void test() {
        m();
    }
}
