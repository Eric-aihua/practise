package com.eric.hadoop.hdfs;

import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

/**
 * 可以读取某个HDFS文件中的内容
 * 执行方法
 * 1：先查看文件 hadoop fs -ls output2
 * 2：执行程序hadoop jar hadoop-0.0.1-SNAPSHOT.jar output2/part-00000
 * @author Eric.sunah
 * 2014年11月27日
 *
 */
public class HDFSUrlCat {

  static {
    URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
  }

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("must contain a hdfs file path");
      System.exit(0);
    }
    InputStream input = null;
    try {
      input = new URL(args[0]).openStream();
      IOUtils.copyBytes(input, System.out, 4096, false);
    } catch (Exception e) {
      e.printStackTrace();
      IOUtils.closeStream(input);
    }
  }

}
