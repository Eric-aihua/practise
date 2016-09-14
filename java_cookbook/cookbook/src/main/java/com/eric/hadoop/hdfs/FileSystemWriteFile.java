package com.eric.hadoop.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileSystemWriteFile {

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("must contain a local file path and dest file path");
      System.exit(0);
    }
    String localPath = args[0];
    String destPath = args[1];
    copyFileFromLocal(localPath, destPath);
  }

  private static void copyFileFromLocal(String localPath, String destPath) {
    InputStream inputStream = null;
    FSDataOutputStream fsDataOutputStream = null;
    try {
      Configuration config = new Configuration();
      inputStream = new FileInputStream(new File(localPath));
      FileSystem fs = FileSystem.get(URI.create(destPath), config);
      //true参数标示会overwrite
      fsDataOutputStream = fs.create(new Path(destPath), true, 256, new Progressable() {
        public void progress() {
          System.out.println(new Date() + " copying file from local....");
        }
      });
      IOUtils.copyBytes(inputStream, fsDataOutputStream, 256, false);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeStream(fsDataOutputStream);
      IOUtils.closeStream(inputStream);
    }
  }
}
