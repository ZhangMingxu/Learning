package com.xufree.learning.hadoop.sequencefile;

import com.xufree.learning.hadoop.hdfs.HDFSClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

/**
 * @author zhangmingxu ON 20:39 2019-04-22
 **/
public class WriteDemo {
    private static final String[] DATA = {
            "SequenceFile为二进制键-值对提供了一个持久化的数据结构",
            "存储在SequenceFile中的键和值并不一定需要是Writable类型。只要能被Serialization序列化和反序列化",
            "现在编写一个写入SequenceFile到Hadoop中的Demo",
            "这些话就是数据,会写入HDFS中"
    };

    public static void main(String[] args) {
        String inputUrl = "/SequenceFile/WriteDemo/numbers.seq";
        Path inputPath = new Path(inputUrl);
        Configuration configuration = HDFSClient.getConfiguration();
        IntWritable key = new IntWritable();
        Text value = new Text();
        SequenceFile.Writer writer = null;
        try {
            writer = SequenceFile.createWriter(configuration
                    , SequenceFile.Writer.file(inputPath)
                    , SequenceFile.Writer.keyClass(key.getClass())
                    , SequenceFile.Writer.valueClass(value.getClass())
                    , SequenceFile.Writer.replication((short) 1));
            for (int i = 0; i < 100; i++) {
                key.set(100 - i);
                value.set(DATA[i % DATA.length]);
                writer.append(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(writer);
        }
    }
}
