package com.xufree.learning.hadoop.mapreduce.loganalyse;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author zhangmingxu ON 16:22 2019-03-05
 **/
public class LogAnalyseReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0;
        for (IntWritable value : values) {
            count++;
        }
        context.write(key, new LongWritable(count));
    }
}
