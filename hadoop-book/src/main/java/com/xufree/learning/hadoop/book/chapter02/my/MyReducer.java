package com.xufree.learning.hadoop.book.chapter02.my;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double max = Double.MAX_VALUE;
        for (DoubleWritable value : values) {
            max = Math.max(max, value.get());
        }
        context.write(key, new DoubleWritable(max));
    }
}
