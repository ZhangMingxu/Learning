package com.xufree.learning.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhangmingxu ON 20:35 2019-05-09
 **/
public class WebSocketClient {

    private static Socket client;
    private static volatile boolean isDone = false;

    @Override
    public String toString() {
        return super.toString();
    }

    static {
        try {
            client = new Socket("localhost", 9090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startListenInput();
        startListenResponse();

    }

    private static void startListenInput() {
        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                while (true) {
                    if (scanner.hasNext()) {
                        String input = scanner.next();
                        out.println(input);
                        if ("exit".equals(input)) {
                            isDone = true;
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
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
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
