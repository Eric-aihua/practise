package com.eric.hadoop.map;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MaxTemperatureMapper extends MapReduceBase implements
    Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;

  public void map(LongWritable fileOffset, Text lineRecord,
      OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
    System.out.println("##Processing Record:" + lineRecord.toString());
    String line = lineRecord.toString();
    String year = line.substring(15, 19);
    int temperature;
    if (line.charAt(87) == '+') {
      temperature = Integer.parseInt(line.substring(88, 92));
    } else {
      temperature = Integer.parseInt(line.substring(87, 92));
    }
    String quality = line.substring(92, 93);
    if (temperature != MISSING && quality.matches("[01459]")) {
      output.collect(new Text(year), new IntWritable(temperature));
    }
  }

}
