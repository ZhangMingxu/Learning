package com.xufree.learning.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhangmingxu ON 20:44 2019-04-22
 **/
public class HDFSClient {
    private static final Logger logger = LoggerFactory.getLogger(HDFSClient.class);

    private static Configuration configuration;
    private static FileSystem client;

    static {
        configuration = new Configuration();
        configuration.addResource("core-site.xml");
        configuration.addResource("hdfs-site.xml");
        configuration.addResource("yarn-site.xml");
        configuration.addResource("mapred-site.xml");
        try {
            client = FileSystem.newInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static FileSystem getClient() {
        if (client == null) {
            throw new RuntimeException("实例化HDFS客户端失败");
        }
        return client;
    }

    public static void printAllConfig() {
        configuration.forEach(entry -> logger.info("读取配置[{}:{}]", entry.getKey(), entry.getValue()));
    }

    public String getConfig(String key) {
        return configuration.get(key);
    }
}
