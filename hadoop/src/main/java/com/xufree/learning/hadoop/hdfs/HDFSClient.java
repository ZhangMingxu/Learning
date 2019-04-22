package com.xufree.learning.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

/**
 * @author zhangmingxu ON 20:44 2019-04-22
 **/
public class HDFSClient {
    private static Configuration configuration;
    private static FileSystem client;

    static {
        configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://zhangmingxudeMacBook-Pro.local:9000");
        try {
            client = FileSystem.newInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getConfiguration(){
        return configuration;
    }

    public static FileSystem getClient() {
        if (client == null) {
            throw new RuntimeException("实例化HDFS客户端失败");
        }
        return client;
    }
}
