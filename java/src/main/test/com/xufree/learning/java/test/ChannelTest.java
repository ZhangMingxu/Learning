package com.xufree.learning.java.test;

import com.xufree.learning.java.channel.FileChannel;
import org.junit.Test;

import java.io.IOException;

public class ChannelTest {
    @Test
    public void FileChannelTest() throws IOException {
        FileChannel filechannel = new FileChannel("/Users/zhangmingxu/Documents/#jr_award建库语句 2018-08-01版");
        filechannel.printAll();
        filechannel.close();
    }
}
