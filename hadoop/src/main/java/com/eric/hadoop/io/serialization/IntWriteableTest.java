package com.eric.hadoop.io.serialization;

import org.apache.hadoop.io.IntWritable;

/**
 * 验证IntWritable 序列化
 * 
 * @author Eric.sunah 2014年12月4日
 * 
 */
public class IntWriteableTest {
  public static void main(String[] args) {
    Integer inputValue = 163;
    IntWritable intWritable = new IntWritable(inputValue);
    // output serialize result
    System.out.println(SerializationUtils.testSerialization(intWritable));
    // verify deserialize result
    System.out.println(SerializationUtils.testDeSerialization(SerializationUtils
        .serialization(intWritable)));
  }


}
