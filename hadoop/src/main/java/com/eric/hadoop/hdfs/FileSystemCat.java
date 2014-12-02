package com.eric.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * 为了解决有些环境无法在HDFSUrlCat中设置FsUrlStreamHandlerFactory的问题
 * 
 * @author Eric.sunah 2014年11月28日
 * 
 */
public class FileSystemCat {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("must contain a hdfs file path");
      System.exit(0);
    }
    FileSystem fs = null;
    FSDataInputStream fsInputStream = null;
    try {
      Configuration conf = new Configuration();
      fs = FileSystem.get(URI.create(args[0]), conf);
      fsInputStream = fs.open(new Path(args[0]));
      repeatPrint(fsInputStream, conf);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeStream(fsInputStream);
    }
  }

  private static void repeatPrint(FSDataInputStream fsInputStream, Configuration conf)
      throws IOException {
    IOUtils.copyBytes(fsInputStream, System.out, conf, false);
    fsInputStream.seek(0);
    System.out.println("###"+fsInputStream.available());
    for (int i = 0; i < fsInputStream.available(); i++) {
      IOUtils.copyBytes(fsInputStream, System.out, conf, false);
    }
  }

}
