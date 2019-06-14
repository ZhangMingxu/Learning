package com.xufree.learning.netty.demo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
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
public class EchoServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Map<String, ChannelHandlerContext> clientMap = new ConcurrentHashMap<>();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        String clientId = getId(ctx);
        System.out.println("clientId=" + clientId + " Registered");
        clientMap.put(clientId, ctx);
        sayHello(clientId);
        sayToOther(clientId, clientId + "登陆了");
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        String clientId = getId(ctx);
        System.out.println("clientId=" + clientId + " Unregistered");
        clientMap.remove(clientId);
        sayToOther(clientId, clientId + "退出了");
    }

    /**
     * 对于每个传入的消息都要调用
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//
//    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("messageReceived" + msg);
//        ByteBuf in = (ByteBuf) msg;
//        String s = in.toString(CharsetUtil.UTF_8);
//        if (s.equals("exit\r\n")) {
//            sendMessage(ctx, "再见");
//            ctx.close();
//            return;
//        }
//        String[] strings = s.split(" ");
//        if (strings.length != 2) {
//            sendMessage(ctx, "命令格式错误");
//            return;
//        }
//        String to = strings[0];
//        String message = strings[1];
//        speakTo(getId(ctx), to, message);
    }

//    /**
//     * 通知ChannelInboundHandler最后一次对channel-Read()的调用是当前批量读取中的最后一条消息
//     */
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER); //消息统一发送
////        .addListener(ChannelFutureListener.CLOSE);  //关闭channel
//    }

    /**
     * 处理异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println(getId(ctx) + "调用异常 msg:" + cause.getMessage());
    }

    private void speakTo(String from, String to, String msg) {
        ChannelHandlerContext toContext = clientMap.get(to);
        if (toContext == null) {
            sendMessage(clientMap.get(from), to + "未注册或已下线");
            return;
        }
        sendMessage(toContext, from + " 对你说 " + msg);
    }

    private void sayHello(String clientId) {
        ChannelHandlerContext client = clientMap.get(clientId);
        sendMessage(client, "欢迎登陆");
        sendMessage(client, "你的客户端Id为" + getId(client) + "");
        sendMessage(client, "现在已经注册的客户端有:");
        clientMap.forEach((key, value) -> {
            if (!key.equals(clientId)) {
                sendMessage(client, key);
            }
        });
    }

    private void sayToOther(String selfId, String msg) {
        clientMap.forEach((key, value) -> {
            if (!key.equals(selfId)) {
                sendMessage(value, msg);
            }
        });
    }

    private void sendMessage(ChannelHandlerContext client, String message) {
        ChannelFuture channelFuture = client.writeAndFlush(Unpooled.copiedBuffer(message + "\n", CharsetUtil.UTF_8));
        //增加监听 判断是否写成功了
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (!future.isSuccess()) { //如果没有成功 输出错误
                future.cause().printStackTrace();
            }
        });
    }

    private String getId(ChannelHandlerContext ctx) {
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        return socketAddress.getHostString() + ":" + socketAddress.getPort();
    }
}
