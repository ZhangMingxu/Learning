package com.xufree.learning.hadoop.test.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Stack;

/**
 * @author zhangmingxu ON 17:22 2019-03-15
 **/
public class FilterStatusDemo {
    private FileSystem client = null;

    @Before
    public void initFileSystem() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        conf.set("dfs.client.use.datanode.hostname", "true");
        client = FileSystem.get(conf);
    }

    @After
    public void closeClient() throws IOException {
        client.close();
    }

    @Test
    public void testQueryFilerStat() throws IOException {
        Path root = new Path("/");
        queryFilerStat(client.getFileLinkStatus(root));
        System.out.println("====================");
        queryFilerStatByStack(client.getFileLinkStatus(root));
    }

    private void queryFilerStat(FileStatus file) throws IOException {
        if (file.isDirectory()) {
            FileStatus[] files = client.listStatus(file.getPath());
            if (files != null && files.length > 0) {
                for (FileStatus fileStatus : files) {
                    queryFilerStat(fileStatus);
                }
            } else {
                System.out.println("空文件夹:" + file.getPath().toString());
            }
        } else {
            System.out.println("文件:" + file.getPath().toString());
        }
    }

    private void queryFilerStatByStack(FileStatus file) throws IOException {
        Stack<FileStatus> stack = new Stack<>();
        stack.push(file);
        while (!stack.empty()) {
            FileStatus cur = stack.pop();
            if (cur.isDirectory()) {
                FileStatus[] files = client.listStatus(cur.getPath());
                if (files != null && files.length > 0) {
                    for (FileStatus fileStatus : files) {
                        stack.push(fileStatus);
                        System.out.println("push:" + fileStatus.getPath().toString());

                    }
                } else {
                    System.out.println("空文件夹:" + cur.getPath().toString());
                }
            } else {
                System.out.println("文件:" + cur.getPath().toString());
            }
        }

    }

}
