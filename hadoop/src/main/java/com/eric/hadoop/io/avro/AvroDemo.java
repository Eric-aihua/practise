package com.eric.hadoop.io.avro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.eric.hadoop.io.avro.entity.User;



/**
 * 主要用来展示通过avro对数据进行读写操作
 * @author Eric.sunah
 * 2014年12月8日
 *
 */
public class AvroDemo {

  /**
   * user.avsc的namespace为包名，且必须要正确，否则会导致无法产生源码
   */
  private static String SCHEMA_FILE = "user.avsc";
  private static String RESULT_FILE = "./Result.txt";
  private static String RESULT_FILE2 = "./Result2.txt";

  public static void main(String[] args) {
    AvroDemo demo = new AvroDemo();
    System.out.println("通过schema文件进行序列化实验：");
    demo.seriaNoSource();
    demo.deSeriaNoSource();
    System.out.println("通过源文件进行序列化实验：");
    demo.seriaBySource();
    demo.deSeriaBySource();
  }



  /**
   * 通过无源码的方式，将内容写到Result.txt文件中
   */
  public void seriaNoSource() {
    try {
      Schema schema = getSchema();
      GenericRecord user1 = new GenericData.Record(schema);
      user1.put("name", "Alyssa");
      user1.put("favorite_number", 256);
      // Leave favorite color null

      GenericRecord user2 = new GenericData.Record(schema);
      user2.put("name", "Ben");
      user2.put("favorite_number", 7);
      user2.put("favorite_color", "red");

      DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
      DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(writer);
      dataFileWriter.create(schema, new File(RESULT_FILE));

      dataFileWriter.append(user1);
      dataFileWriter.append(user2);
      dataFileWriter.close();
      System.out.println("Serialization finished!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  /**
   * 通过无源码的方式，从Result.txt反序列化读取数据
   */
  public void deSeriaNoSource() {
    try {
      DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(getSchema());
      DataFileReader<GenericRecord> dataReader =
          new DataFileReader<GenericRecord>(new File(RESULT_FILE), reader);
      while (dataReader.hasNext()) {
        System.out.println(dataReader.next());
      }
      System.out.println("Deserilazation Finished!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void seriaBySource() {
    try {
      User user1 = new User();
      user1.setName("Alyssa");
      user1.setFavoriteNumber(256);
      User user2 = new User("Ben", 7, "red");
      User user3 =
          User.newBuilder().setName("Charlie").setFavoriteColor("blue").setFavoriteNumber(null)
              .build();

      // Serialize user1, user2 and user3 to disk
      DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
      DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
      dataFileWriter.create(user1.getSchema(), new File(RESULT_FILE2));
      dataFileWriter.append(user1);
      dataFileWriter.append(user2);
      dataFileWriter.append(user3);
      dataFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  public void deSeriaBySource() {
    // Deserialize Users from disk
    DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
    DataFileReader<User> dataFileReader;
    try {
      dataFileReader = new DataFileReader<User>(new File(RESULT_FILE2), userDatumReader);
      User user = null;
      while (dataFileReader.hasNext()) {
        // Reuse user object by passing it to next(). This saves us from
        // allocating and garbage collecting many objects for files with
        // many items.
        user = dataFileReader.next(user);
        System.out.println(user);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private Schema getSchema() {
    Schema schema = null;
    try {
      schema = new Schema.Parser().parse(getClass().getResourceAsStream(SCHEMA_FILE));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return schema;
  }
}
