package com.xufree.learning.hadoop.test.mr;

import com.xufree.learning.hadoop.mapreduce.loganalyse.LogAnalyseMapper;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import  org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

/**
 * 测试日志分析Mapper
 *
 * @author zhangmingxu ON 21:22 2019-04-23
 **/
public class LogAnalyseMapperTest {

    @Test
    public void test() throws IOException {
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new LogAnalyseMapper())
                .withInput(new LongWritable(0),new Text("[RiskServiceGeneralImpl] lalalal"))
                .withInput(new LongWritable(1),new Text("[RiskServiceGeneralImpl] lalalal1"))
                .withInput(new LongWritable(1),new Text("[BlackServiceGeneralImpl] lalalal1"))
                .withOutput(new Text("BlackServiceGeneralImpl"),new IntWritable(1))
                .withOutput(new Text("RiskServiceGeneralImpl"),new IntWritable(2))
                .runTest();
    }
}
