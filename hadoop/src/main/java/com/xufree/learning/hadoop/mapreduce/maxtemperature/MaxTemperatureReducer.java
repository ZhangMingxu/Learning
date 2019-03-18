package com.xufree.learning.hadoop.mapreduce.maxtemperature;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author zhangmingxu ON 15:19 2019-03-05
 **/
public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable value : values) {
            int temp = value.get();
            max = Integer.max(max, temp);
        }
        context.write(key, new IntWritable(max));
    }
}
