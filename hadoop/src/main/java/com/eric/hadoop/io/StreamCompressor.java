package com.eric.hadoop.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

/**
 * 利用System.out创建CompressionOutputStream，然后对其进行压缩,将压缩的结果放到指定的文件中
 * 执行方法：date|hadoop jar hadoop-0.0.1-SNAPSHOT.jar org.apache.hadoop.io.compress.GzipCodec ./date.gz
 * 
 * @author Eric.sunah 2014年12月2日
 */
public class StreamCompressor {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("must contain a Compress codec class full name and output file");
      System.exit(0);
    }
    String compressionCodecClassName = args[0];
    String outputFile = args[1];
    CompressionOutputStream outputStream = null;
    try {
      // 指定压缩算法
      Class<?> compressionCodecClass = Class.forName(compressionCodecClassName);
      Configuration config = new Configuration();
      CompressionCodec compressCodec =
          (CompressionCodec) ReflectionUtils.newInstance(compressionCodecClass, config);
      outputStream = compressCodec.createOutputStream(new FileOutputStream(new File(outputFile)));
      IOUtils.copyBytes(System.in, outputStream, 4096, false);
      outputStream.finish();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
