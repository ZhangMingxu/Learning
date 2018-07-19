package com.xufree.learning.hadoop.book.chapter02.my;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyJob {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2) {
            System.err.println("Usage: MaxTemperatureWithCombiner <input path> " +
                    "<output path>");
            System.exit(-1);
        }
        Job myjob = Job.getInstance();
        myjob.setJobName("My Job");
        myjob.setJarByClass(MyJob.class);

        FileInputFormat.addInputPath(myjob, new Path(args[0]));
        FileOutputFormat.setOutputPath(myjob, new Path(args[1]));

        myjob.setMapperClass(MyMapper.class);
        myjob.setReducerClass(MyReducer.class);

        myjob.setOutputKeyClass(Text.class);
        myjob.setOutputValueClass(DoubleWritable.class);

        System.exit(myjob.waitForCompletion(true) ? 0 : 1);
    }
}
