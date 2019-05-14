package com.xufree.learning.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程接收client请求
 * @author zhangmingxu ON 18:13 2019-05-13
 **/
public class MultiThreadSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true) {
            Socket socket = serverSocket.accept();
            process(socket);
        }
    }

    private static void process(Socket socket) {
        new Thread(new ProcessThread(socket)).start();
    }

    static class ProcessThread implements Runnable {
        private Socket socket;

        public ProcessThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String req;
                while ((req = in.readLine()) != null) {
                    String response = "thread:" + Thread.currentThread().getName() + " got " + req;
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
