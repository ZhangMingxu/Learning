package com.xufree.learning.java.io;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Scanner;

/**
 * 模拟tail -f 命令
 * 同时监听控制台输入 exit退出
 *
 * @author zhangmingxu ON 10:01 2019-03-28
 **/
public class TailFCommand {
    private static volatile boolean flag = true;

    public static void main(String[] args) throws IOException {
        startListen();
        String inputPath = "/export/Logs/award-mng.jd.local/info.log";
        InputStream in = new FileInputStream(inputPath);
        int len;
        byte[] buffer = new byte[4096];
        while (flag) {
            len = in.read(buffer);
            if (len != -1) {
                System.out.write(buffer, 0, len);
            }
        }
    }

    private static void startListen() {
        Thread thread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (flag) {
                String command = scanner.next();
                if (StringUtils.isNotBlank(command)) {
                    if ("exit".equals(command.toLowerCase())) {
                        flag = false;
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    //不需要回车
    private static void startListenNoEntry() {
        Thread thread = new Thread(() -> {

        });
        thread.setDaemon(true);
        thread.start();
    }
}
