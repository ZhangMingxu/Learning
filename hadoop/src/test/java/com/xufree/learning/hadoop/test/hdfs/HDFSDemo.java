package com.xufree.learning.hadoop.test.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author zhangmingxu ON 22:35 2019-02-20
 **/
public class HDFSDemo {
    private FileSystem client = null;

    @Before
    public void initFileSystem() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        conf.set("dfs.client.use.datanode.hostname", "true");
        client = FileSystem.get(conf);
    }

    @Test
    public void testMkDir() throws Exception {
        client.mkdirs(new Path("/testMkDir"));
        client.close();
    }

    @Test
    public void testUpload() throws Exception {
        InputStream input = new FileInputStream("/Users/zhangmingxu/Desktop/a.txt");
        OutputStream output = client.create(new Path("/input/a.txt"));
        IOUtils.copyBytes(input, output, 1024);
    }

    @Test
    public void testUploadWithProcess() throws IOException {
        InputStream input = new FileInputStream("/Users/zhangmingxu/Desktop/a.txt");
        OutputStream output = client.create(new Path("/input/aWithProcess.txt"), () -> System.out.println("."));
        IOUtils.copyBytes(input, output, 1024);
    }

    @Test
    public void testCopyFromLocalFile() {
        try {
            client.copyFromLocalFile(true, true,
                    new Path("/Users/zhangmingxu/Desktop/a.txt")
                    , new Path("/input/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadFile() throws IOException {
        Path path = new Path("/input/aWithProcess.txt");
        FSDataInputStream inputStream = client.open(path);
        byte[] buffer = new byte[2048];
        int readed;
        while ((readed =inputStream.read(buffer)) >=0){
            System.out.write(buffer,0,readed);
        }
//        IOUtils.copyBytes(inputStream,System.out,2048);
        IOUtils.closeStream(inputStream);
    }
    @After
    public void closeClient() throws IOException {
        client.close();
    }

}
