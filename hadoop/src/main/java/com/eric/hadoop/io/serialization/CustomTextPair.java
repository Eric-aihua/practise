package com.eric.hadoop.io.serialization;


/**
 * 自定义的字符串对的Writable
 * 
 * @author Eric.sunah 2014年12月5日
 * 
 */
public class CustomTextPair {
  public static void main(String[] args) {
    TextPair textPair = new TextPair("Hadoop", "Spark");
    // output serialize result
    System.out.println(SerializationUtils.testSerialization(textPair));
    // verify deserialize result
    System.out.println(SerializationUtils.testTextPairDeSerialization(SerializationUtils
        .serialization(textPair)));
  }
}




