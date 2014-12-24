package com.eric.hadoop.jobconfig;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import com.eric.hadoop.map.MaxTemperatureMapper;
import com.eric.hadoop.reduce.MaxTemperatureReduce;


public class MaxTemperature {
  public static void main(String[] args) throws IOException {
    JobConf conf = new JobConf(MaxTemperature.class);
    conf.setJobName("Get Max Temperature!");
    if (args.length != 2) {
      System.err.print("Must contain 2 params:inputPath OutputPath");
      System.exit(0);
    }

    FileInputFormat.addInputPaths(conf, args[0]);
    FileOutputFormat.setOutputPath(conf, new Path(args[1]));

    conf.setMapperClass(MaxTemperatureMapper.class);
    conf.setReducerClass(MaxTemperatureReduce.class);
    // 设置combiner 类
    conf.setCombinerClass(MaxTemperatureReduce.class);

    // 对输出结果进行压缩
    conf.setBoolean("mapred.output.compress", true);
    conf.setClass("mapred.output.compression.codec", GzipCodec.class, CompressionCodec.class);
    // 对Map的输出结果进行压缩
    conf.setCompressMapOutput(true);
    conf.setMapOutputCompressorClass(GzipCodec.class);

    // 添加HPROF分析，执行完hadoop jar hadoop-0.0.1-SNAPSHOT.jar testdata/1902 data/output1后，可以在执行该命令的文件夹中看到profile文件
    conf.setProfileEnabled(true);
    conf.setProfileParams("-agentlib:hprof=cpu=samples,heap=sites,depth=6,force=n,thread=y,verbose=n,file=%s");
    conf.setProfileTaskRange(true, "0-2");

    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(IntWritable.class);
    JobClient.runJob(conf);
  }
}
