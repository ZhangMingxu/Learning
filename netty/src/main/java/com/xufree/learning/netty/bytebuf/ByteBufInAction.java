package com.xufree.learning.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.netty.util.CharsetUtil;

/**
 * @author zhangmingxu ON 10:07 2019-06-05
 **/
public class ByteBufInAction {

    /**
     * 支撑数组模式的缓冲区，它能在没有池化的情况下快速的分配和释放
     */
    public void backingArray(ByteBuf byteBuf) {
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            int offset = byteBuf.arrayOffset() + byteBuf.readerIndex();//计算第一个字节便宜量
            int length = byteBuf.readableBytes();
            handleArray(array, offset, length);
        } else {
            //如果byteBuf.hasArray返回false再尝试访问数组会触发一个UnsupportedOperationException
        }
    }

    /**
     * 直接缓冲区 不适用JVM怼内存 直接使用本地内存
     */
    public void directCache(ByteBuf byteBuf) {
        if (!byteBuf.hasArray()) { //检查是否有支撑数组，如果没有说明使用了直接缓冲区
            int length = byteBuf.readableBytes();
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);
            handleArray(array, 0, length);
        }
    }

    /**
     * 如何读取所有可读字节
     */
    public void readReadable(ByteBuf byteBuf) {
        while (byteBuf.isReadable()) {
            System.out.println(byteBuf.readByte());
        }
    }

    /**
     * 查找
     */
    public void serch(ByteBuf byteBuf) {
        //查找回车 /r
        int crIndex = byteBuf.forEachByte(ByteBufProcessor.FIND_CR);

        //查找null
        int nullIndex = byteBuf.forEachByte(ByteBufProcessor.FIND_NUL);

        //自定义查找 查找a
        int selfIndex = byteBuf.forEachByte(value -> {
            return value == 'a';
        });
    }

    /**
     * 测试分片
     */
    public void slice(ByteBuf byteBuf) {
        ByteBuf sliced = byteBuf.slice(0, 16); //分片
        System.out.println(sliced.toString(CharsetUtil.UTF_8));
        byteBuf.setByte(0, 'j'); //对老值的修改会影响新的分片对象
        System.out.println(byteBuf.getByte(0) == sliced.getByte(0)); //因为是共享的所以为true
    }

    /**
     * 测试复制 复制出来的对象是全新的 不是底层数据不共享
     */
    public void copy(ByteBuf byteBuf) {
        ByteBuf copy = byteBuf.copy(0, 15);
        System.out.println(copy.toString(CharsetUtil.UTF_8));
        byteBuf.setByte(0, 'j'); //对老值的修改不会影响新的复制对象
        System.out.println(byteBuf.getByte(0) == copy.getByte(0)); //因为是全新的复制所以为false
    }

    private void handleArray(byte[] bytes, int offset, int length) {
        //doSomeThing
    }
}
