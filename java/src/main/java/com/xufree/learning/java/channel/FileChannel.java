package com.xufree.learning.java.channel;

import com.google.common.base.Preconditions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannel {
    private static final int DEFAULT_CAPACITY = 2048;
    private RandomAccessFile file;
    private java.nio.channels.FileChannel fileChannel;
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private CharsetDecoder decoder;

    public FileChannel(String path) throws FileNotFoundException {
        this(path, null);
    }

    public FileChannel(String path, Integer capacity) throws FileNotFoundException {
        int capacity1 = capacity == null ? DEFAULT_CAPACITY : capacity;
        Preconditions.checkNotNull(path, "文件路径不能为空");
        file = new RandomAccessFile(path, "rw");
        fileChannel = file.getChannel();
        byteBuffer = ByteBuffer.allocate(capacity1);
        charBuffer = CharBuffer.allocate(capacity1);
        decoder = Charset.forName("utf-8").newDecoder();
    }

    public void printAll() throws IOException {
        int byteRead = fileChannel.read(byteBuffer);
        while (byteRead > 0) {
            byteBuffer.flip();
            decoder.decode(byteBuffer,charBuffer,false);
            charBuffer.flip();
            while (charBuffer.hasRemaining()) {
                System.out.print(charBuffer.get());
            }
            byteBuffer.clear();
            charBuffer.clear();
            byteRead = fileChannel.read(byteBuffer);
        }
    }

    public void close() throws IOException {
        file.close();
    }
}
