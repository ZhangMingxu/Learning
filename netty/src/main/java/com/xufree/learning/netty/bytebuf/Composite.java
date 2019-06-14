package com.xufree.learning.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 复合模式
 *
 * @author zhangmingxu ON 20:22 2019-06-04
 **/
public class Composite {
    private CompositeByteBuf message = Unpooled.compositeBuffer();

    //初始化
    public void init(ByteBuf header, ByteBuf body) {
        message.addComponents(header, body);
        message.removeComponent(0);
    }

    //读取
    public void process() {
        int length = message.readableBytes();
        byte[] array = new byte[length];
        message.getBytes(message.readerIndex(), array);
        handleArray(array, 0, length);
    }

    private void handleArray(byte[] bytes, int offset, int length) {
        //doSomeThing
    }
}
