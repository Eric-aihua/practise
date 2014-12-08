package com.eric.hadoop.io.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.StringUtils;

public class SerializationUtils {
  public static String testSerialization(Writable intWritable) {
    return StringUtils.byteToHexString(serialization(intWritable));
  }

  public static Object testDeSerialization(byte[] bytes) {
    return deSerialization(bytes);
  }

  public static Object testTextPairDeSerialization(byte[] bytes) {
    return deTextPairSerialization(bytes);
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
  public static Object deSerialization(byte[] bytes) {
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

  /**
   * 对字节进行反序列化
   * 
   * @param bytes
   * @return
   */
  public static Object deTextPairSerialization(byte[] bytes) {
    ByteArrayInputStream byteinPutStream = new ByteArrayInputStream(bytes);
    DataInputStream os = new DataInputStream(byteinPutStream);
    TextPair iw = new TextPair();
    try {
      iw.readFields(os);
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return iw.toString();
  }
}
