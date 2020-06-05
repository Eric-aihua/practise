package com.eric.hadoop.jobconfig;

import com.eric.hadoop.map.WordCountMapper;
import com.eric.hadoop.reduce.WordCountReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordCount extends Configured implements Tool{


    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new WordCount(),args);
        System.exit(exitCode);
    }

    public int run(String[] strings) throws Exception {
        Configuration config = new Configuration();
        config.set("fs.default.name","file:///");
        config.set("mapred.job.tracker","local");
        config.set("mapreduce.framework.name","local");
//        config.set("mapreduce.framework.name", "yarn");//集群的方式运行，非本地运行
//        config.set("mapreduce.app-submission.cross-platform", "true");//意思是跨平台提交，在windows下如果没有这句代码会报错 "/bin/bash: line 0: fg: no job control"，去网上搜答案很多都说是linux和windows环境不同导致的一般都是修改YarnRunner.java，但是其实添加了这行代码就可以了。
//        config.set("mapreduce.job.jar","D:\\wordcount\\out\\artifacts\\wordcount_jar\\wordcount.jar");

//        Job job = Job.getInstance(config);
        Job job = new Job(config,"wordcount");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //要处理的数据输入与输出地址
        FileInputFormat.setInputPaths(job,"word_count_input_data");
        FileOutputFormat.setOutputPath(job,new Path("output/test_wordcount"));

        return job.waitForCompletion(true) ? 0:1;


    }
}