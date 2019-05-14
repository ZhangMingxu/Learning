package com.xufree.learning.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 使用java.net实现一个简单的端口监听程序
 *
 * @author zhangmingxu ON 20:20 2019-05-09
 **/
public class WebSocketServer {
    private static Socket server;
    private static volatile boolean isDone = false;
    static {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            server = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        startListenInput();
        startListenResponse();

    }

    private static void startListenInput() {
        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                PrintWriter out = new PrintWriter(server.getOutputStream(), true);
                while (true) {
                    if (scanner.hasNext()) {
                        String input = scanner.next();
                        out.println(input);
                        if ("exit".equals(input)) {
                            return;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void startListenResponse() {
        new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                while (!isDone) {
                    String response = in.readLine();
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
