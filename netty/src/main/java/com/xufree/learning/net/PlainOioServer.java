package com.xufree.learning.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 使用OIO创建服务器
 * 向客户端写入Hi!
 *
 * @author zhangmingxu ON 17:33 2019-05-28
 **/
public class PlainOioServer {
    public static void main(String[] args) throws IOException {
        final ServerSocket socket = new ServerSocket(9090);
        for (; ; ) {
            final Socket clientSocket = socket.accept();
            new Thread(() -> {
                try (OutputStream out = clientSocket.getOutputStream()) {
                    out.write("Hi!".getBytes(Charset.defaultCharset()));
                    out.flush();
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
