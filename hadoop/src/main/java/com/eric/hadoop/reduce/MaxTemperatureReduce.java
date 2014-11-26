package com.eric.hadoop.reduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MaxTemperatureReduce extends MapReduceBase implements
    Reducer<Text, IntWritable, Text, IntWritable> {

  public void reduce(Text year, Iterator<IntWritable> temperatures,
      OutputCollector<Text, IntWritable> output, Reporter arg3) throws IOException {
    int maxTemperature = Integer.MIN_VALUE;
    System.out.println("##Processing temperatures:" + temperatures);
    while (temperatures.hasNext()) {
      maxTemperature = Math.max(maxTemperature, temperatures.next().get());
    }
    output.collect(year, new IntWritable(maxTemperature));
  }

}
