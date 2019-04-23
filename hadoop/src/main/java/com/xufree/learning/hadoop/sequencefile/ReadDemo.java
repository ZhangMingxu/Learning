package com.xufree.learning.hadoop.sequencefile;


import com.xufree.learning.hadoop.hdfs.HDFSClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangmingxu ON 21:20 2019-04-22
 **/
public class ReadDemo {
    private static final Logger logger = LoggerFactory.getLogger(ReadDemo.class);

    public static void main(String[] args) {
        String inputUrl = "/SequenceFile/WriteDemo/numbers.seq";
        Configuration configuration = HDFSClient.getConfiguration();
        SequenceFile.Reader reader = null;
        try {
            reader = new SequenceFile.Reader(configuration, SequenceFile.Reader.file(new Path(inputUrl)));
            Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
            Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), configuration);
            long position = reader.getPosition();
            while (reader.next(key, value)) {
                String syncSeen = reader.syncSeen() ? "*" : ""; //获取同步点
                logger.info("[{}{}]    {}    {}", position, syncSeen, key, value);
                position = reader.getPosition();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(reader);
        }
    }
}
