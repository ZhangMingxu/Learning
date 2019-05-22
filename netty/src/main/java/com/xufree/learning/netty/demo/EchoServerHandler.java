package com.xufree.learning.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 调用顺序
 * 链接时: channelRegistered->channelActive
 * 有数据流入时: channelRead->channelReadComplete
 * 断开链接时: channelUnregistered->handlerRemoved
 *
 * @author zhangmingxu ON 20:44 2019-05-16
 **/
//标记一个ChannelHandler可以被多个Channel安全的共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {
    private static final Map<String, ChannelHandlerContext> clientMap = new ConcurrentHashMap<>();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        String clientId = getId(ctx);
        System.out.println("clientId=" + clientId + " Registered");
        clientMap.put(clientId, ctx);
        ctx.writeAndFlush("clientId=" + clientId);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        System.out.println("connect");
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        String clientId = getId(ctx);
        System.out.println("clientId=" + clientId + " Unregistered");
        clientMap.remove(clientId);
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        super.handlerRemoved(ctx);
    }

    /**
     * 对于每个传入的消息都要调用
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        String s = in.toString(CharsetUtil.UTF_8);
        System.out.println("channelRead->" + s);
//        String[] strings = s.split(" ");
//        String to = strings[0];
//        String message = strings[1];
        ctx.write(s);
        //speakTo(getId(ctx), to, message);
    }

    /**
     * 通知ChannelInboundHandler最后一次对channel-Read()的调用是当前批量读取中的最后一条消息
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //System.out.println("channelReadComplete");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER); //消息统一发送
        //.addListener(ChannelFutureListener.CLOSE);  //关闭channel
    }

    /**
     * 处理异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //cause.printStackTrace();
//        ctx.channel();
    }

    private String getId(ChannelHandlerContext ctx) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        return socketAddress.getHostString() + ":" + socketAddress.getPort();
    }

    private void speakTo(String from, String to, String msg) {
        ChannelHandlerContext toContext = clientMap.get(to);
        if (toContext == null) {
            clientMap.get(from).writeAndFlush("对方已下线");
            return;
        }
        toContext.writeAndFlush(from + " --> " + msg);
    }
}
