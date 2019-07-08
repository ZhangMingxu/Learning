package com.xufree.learning.java.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.TimeUnit;

/**
 * 线程通信
 *
 * @author zhangmingxu ON 18:24 2019-07-05
 **/
public class ThreadCommunication {

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        pipedReader.connect(pipedWriter);
        Object lock = new Object();
        new Thread(() -> {//读线程
            while (true) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    char[] buf = new char[4096];
                    int len = pipedReader.read(buf);
                    System.out.println("read thread read :" + new String(buf, 0, len));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> { //写线程
            int i = 1;
            while (i < 10) {
                synchronized (lock) {
                    String message = "message-" + i;
                    System.out.println("write thread write :" + message);
                    try {
                        pipedWriter.write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    i++;
                    lock.notify();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
