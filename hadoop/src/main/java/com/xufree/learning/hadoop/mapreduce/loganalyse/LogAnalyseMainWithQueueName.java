package com.xufree.learning.hadoop.mapreduce.loganalyse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author zhangmingxu ON 10:55 2019-03-27
 **/
public class LogAnalyseMainWithQueueName {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //TODO 在这里指定队列名
        JobConf jobConf = new JobConf();
        jobConf.setQueueName("queueName");
        Job job = Job.getInstance(jobConf);
        job.setJarByClass(LogAnalyseMain.class);

        job.setMapperClass(LogAnalyseMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(LogAnalyseReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
