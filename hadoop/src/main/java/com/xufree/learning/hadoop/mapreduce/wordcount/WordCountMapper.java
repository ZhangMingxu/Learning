package com.xufree.learning.hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author zhangmingxu ON 11:04 2019-02-21
 **/
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String data = value.toString();

        String[] words = data.split(" ");

        for(String w:words){
            //             k2            v2
            context.write(new Text(w), new IntWritable(1));
        }
    }
}
