package com.xufree.learning.java.thread;

import com.xufree.learning.common.util.FormatUtil;
import com.xufree.learning.common.util.SleepUtil;

import java.util.Date;

/**
 * 测试过期的suspend（暂停）、resume（继续）、stop（停止）方法
 */
public class Deprecated {
    public static void main(String[] args) {
        Thread printThread = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "Run at:"
                        + FormatUtil.formatTimeFromDate(new Date()));
                SleepUtil.second(1);
            }
        }, "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        SleepUtil.second(3);
        //暂停3S
        printThread.suspend();
        System.out.println("main suspend PrintThread at:"+FormatUtil.formatTimeFromDate(new Date()));
        SleepUtil.second(3);
        //继续运行3S
        printThread.resume();
        System.out.println("main resume PrintThread at:"+FormatUtil.formatTimeFromDate(new Date()));
        SleepUtil.second(3);
        //停止线程
        printThread.stop();
        System.out.println("main stop PrintThread at:"+FormatUtil.formatTimeFromDate(new Date()));
        SleepUtil.second(3);
    }
}
