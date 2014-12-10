package com.eric.hadoop.io.sequencefile;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

/**
 * @author Eric.sunah 2014年12月10日
 * 
 */
public class SequenceFileDemo {

  private static final String OPERA_FILE = "./output.seq";
  /**
   * 随便从网上截取的一段文本
   */
  private static String[] testArray = {
      "<plugin>                                                                     ",
      "  <groupId>org.apache.avro</groupId>                                         ",
      "  <artifactId>avro-maven-plugin</artifactId>                                 ",
      "  <version>1.7.7</version>                                                   ",
      "  <executions>                                                               ",
      "    <execution>                                                              ",
      "      <phase>generate-sources</phase>                                        ",
      "      <goals>                                                                ",
      "        <goal>schema</goal>                                                  ",
      "      </goals>                                                               ",
      "      <configuration>                                                        ",
      "        <sourceDirectory>${project.basedir}/src/main/avro/</sourceDirectory> ",
      "        <outputDirectory>${project.basedir}/src/main/java/</outputDirectory> ",
      "      </configuration>                                                       ",
      "    </execution>                                                             ",
      "  </executions>                                                              ",
      "</plugin>                                                                    ",
      "<plugin>                                                                     ",
      "  <groupId>org.apache.maven.plugins</groupId>                                ",
      "  <artifactId>maven-compiler-plugin</artifactId>                             ",
      "  <configuration>                                                            ",
      "    <source>1.6</source>                                                     ",
      "    <target>1.6</target>                                                     ",
      "  </configuration>                                                           ", "</plugin>"};

  public static void main(String[] args) throws IOException {
    writeSequenceFile(OPERA_FILE);
    readSequenceFile(OPERA_FILE);
  }

  private static void readSequenceFile(String inputFile) throws IOException {
    Configuration config = new Configuration();
    Path path = new Path(inputFile);
    SequenceFile.Reader reader = null;
    try {

      FileSystem fs = FileSystem.get(URI.create(inputFile), config);
      reader = new SequenceFile.Reader(fs, path, config);
      IntWritable key = new IntWritable();
      Text value = new Text();
      while (reader.next(key, value)) {
        System.out.printf("\t%s\t%s\n", key, value);
      }
    } finally {
      IOUtils.closeStream(reader);
    }

  }

  private static void writeSequenceFile(String outputFile) throws IOException {
    Configuration config = new Configuration();
    Path path = new Path(outputFile);
    IntWritable key = new IntWritable();
    Text value = new Text();
    SequenceFile.Writer writer = null;
    try {

      FileSystem fs = FileSystem.get(URI.create(outputFile), config);
      writer = SequenceFile.createWriter(fs, config, path, key.getClass(), value.getClass());
      for (int i = 1; i < 2000; i++) {
        key.set(2000 - i);
        value.set(testArray[i % testArray.length]);
        System.out.printf("[%s]\t%s\t%s\n", writer.getLength() + "", key, value);
        writer.append(key, value);
      }
    } finally {
      IOUtils.closeStream(writer);
    }

  }



}
