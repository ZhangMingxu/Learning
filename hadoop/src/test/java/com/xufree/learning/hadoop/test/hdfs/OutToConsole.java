package com.xufree.learning.hadoop.test.hdfs;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * @author zhangmingxu ON 11:35 2019-03-15
 **/
public class OutToConsole {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        InputStream in = null;
        try {
            in = new URL("hdfs://localhost:9000"+path).openStream();
            IOUtils.copyBytes(in,System.out,4096,false);
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            IOUtils.closeStream(in);
            scanner.close();
        }
    }
}
