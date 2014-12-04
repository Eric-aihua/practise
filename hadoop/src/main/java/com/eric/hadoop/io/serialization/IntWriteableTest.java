package com.eric.hadoop.io.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.StringUtils;

/**
 * 验证IntWritable 序列化
 * 
 * @author Eric.sunah 2014年12月4日
 * 
 */
public class IntWriteableTest {
  public static void main(String[] args) {
    int inputValue = 163;
    IntWritable intWritable = new IntWritable(inputValue);
    // output serialize result
    System.out.println(testSerialization(intWritable));
    // verify deserialize result
    System.out.println(testDeSerialization(serialization(intWritable)) == inputValue);
  }

  private static String testSerialization(IntWritable intWritable) {
    return StringUtils.byteToHexString(serialization(intWritable));
  }

  private static int testDeSerialization(byte[] bytes) {
    return deSerialization(bytes);
  }

  /**
   * 对某个writable进行序列化，返回字节数组
   * 
   * @param writable
   * @return
   */
  public static byte[] serialization(Writable writable) {
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    DataOutputStream os = new DataOutputStream(byteOutputStream);
    try {
      writable.write(os);
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return byteOutputStream.toByteArray();
  }



  /**
   * 对字节进行反序列化
   * 
   * @param bytes
   * @return
   */
  public static int deSerialization(byte[] bytes) {
    ByteArrayInputStream byteinPutStream = new ByteArrayInputStream(bytes);
    DataInputStream os = new DataInputStream(byteinPutStream);
    IntWritable iw = new IntWritable();
    try {
      iw.readFields(os);
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return iw.get();
  }
}
