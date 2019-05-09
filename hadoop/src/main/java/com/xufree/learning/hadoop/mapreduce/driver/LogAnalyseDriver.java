package com.xufree.learning.hadoop.mapreduce.driver;

import com.xufree.learning.hadoop.mapreduce.loganalyse.LogAnalyseMain;
import com.xufree.learning.hadoop.mapreduce.loganalyse.LogAnalyseMapper;
import com.xufree.learning.hadoop.mapreduce.loganalyse.LogAnalyseReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author zhangmingxu ON 21:08 2019-05-07
 **/
public class LogAnalyseDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(LogAnalyseMain.class);

        job.setMapperClass(LogAnalyseMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(LogAnalyseReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new LogAnalyseDriver(), args);
        System.exit(exitCode);
    }
}
