package com.eric.hadoop.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

/**
 * 通过解析本地文件的文件后缀名，推断出类型。推断出类型后对其进行解压
 * @author Eric.sunah
 * 2014年12月2日
 *
 */
public class FileDeCompressor {
  public static void main(String[] args) {
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
      String uri = args[0];
      Configuration config = new Configuration();
      CompressionCodecFactory codecFactory = new CompressionCodecFactory(config);
      Path inputPath = new Path(uri);
      CompressionCodec compreCodec = codecFactory.getCodec(inputPath);
      //使用CompressionCodec判断类型
      String outputUri = codecFactory.removeSuffix(uri, compreCodec.getDefaultExtension());
      inputStream = compreCodec.createInputStream(new FileInputStream(new File(uri)));
      outputStream = new FileOutputStream(new File(outputUri));
      IOUtils.copyBytes(inputStream, outputStream, config);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeStream(inputStream);
      IOUtils.closeStream(outputStream);
    }
  }
}
