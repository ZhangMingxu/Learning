package com.xufree.learning.hadoop.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;

import java.io.IOException;
import java.io.InputStream;


/**
 * 将HDFS上的压缩文件解压后输出到控制台
 *
 * @author zhangmingxu ON 11:27 2019-03-27
 **/
public class UnCompressHDFSToConsole {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        FileSystem client = FileSystem.newInstance(conf);
        Path inputPath = new Path("/output/LogAnalyseWithCompression/part-r-00000.gz");
        InputStream inputStream = client.open(inputPath);
        //获取压缩算法
        CompressionCodecFactory compressionCodecFactory = new CompressionCodecFactory(conf);
        CompressionCodec compressionCodec =  compressionCodecFactory.getCodec(inputPath);
        //解压
        CompressionInputStream compressionInputStream = compressionCodec.createInputStream(inputStream);
        //输出
        IOUtils.copyBytes(compressionInputStream,System.out,4096,false);
        IOUtils.closeStream(compressionInputStream);
        IOUtils.closeStream(inputStream);


    }
}
