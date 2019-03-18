package com.xufree.learning.hadoop.mapreduce.loganalyse;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author zhangmingxu ON 16:07 2019-03-05
 **/
public class LogAnalyseMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String log = value.toString();
        if (StringUtils.isNotBlank(log) && log.startsWith("[")){
            int begin = log.indexOf('[');
            int end = log.indexOf(']');
            String classString = log.substring(begin+1,end);
            context.write(new Text(classString),new IntWritable(1));

        }
    }
}
