package com.xufree.learning.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author zhangmingxu ON 21:01 2019-05-16
 **/
public class EchoServer {
    private final int port;

    private EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("参数错误");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    private void start() throws InterruptedException {
        EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group).channel(NioServerSocketChannel.class) //指定使用NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) //指定接口地址
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(serverHandler); //添加处理器
                        }
                    });
            ChannelFuture future = b.bind().sync(); //异步绑定服务器 调用sync方法阻塞等待直到绑定完成
            future.channel().closeFuture().sync(); //获取Channel的closeFuture，并且阻塞直到完成
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync(); //释放资源
        }
    }
}
