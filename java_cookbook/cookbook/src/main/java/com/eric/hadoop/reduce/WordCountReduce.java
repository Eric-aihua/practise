package com.eric.hadoop.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
        int count = 0 ;
        for (IntWritable value:values) {
            count += value.get();
        }
        context.write(key,new IntWritable((count)));
    }
}
