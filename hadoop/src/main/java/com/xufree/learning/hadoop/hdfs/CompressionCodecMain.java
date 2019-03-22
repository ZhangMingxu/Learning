package com.xufree.learning.hadoop.hdfs;

import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.GzipCodec;

import java.io.*;

/**
 * 测试hadoop压缩
 *
 * @author zhangmingxu ON 17:18 2019-03-22
 **/
public class CompressionCodecMain {
    public static void main(String[] args) throws IOException {
        String inputPath = "/Users/zhangmingxu/Documents/Dump日志/20190110-coinb/java_pid12262.hprof";
        String outputPath = "/Users/zhangmingxu/Documents/Dump日志/20190110-coinb/java_pid12262.hprof.gz";
        InputStream inputStream = new FileInputStream(inputPath);
        OutputStream outputStream = new FileOutputStream(outputPath);
        CompressionCodec compressionCodec = new GzipCodec();
        CompressionOutputStream compressionOutputStream = compressionCodec.createOutputStream(outputStream);
        byte[] buffer = new byte[4096];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            compressionOutputStream.write(buffer, 0, len);
        }
        compressionOutputStream.finish();
    }
}
