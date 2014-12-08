package com.eric.hadoop.io.avro;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;



public class AvroDemo {

  private static String FILE_NAME = "user.avro";

  public void main(String[] args) {
    AvroDemo demo = new AvroDemo();
    demo.seriaNoSource();
    demo.deSeriaNoSource();
    demo.seriaBySource();
    demo.deSeriaBySource();
  }



  public void seriaNoSource() {
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
    
    
  }



  public static void deSeriaNoSource() {

  }

  public static void seriaBySource() {

  }

  public static void deSeriaBySource() {

  }

  private Schema getSchema() {
    Schema schema = null;
    try {
      schema = new Schema.Parser().parse(getClass().getResourceAsStream(FILE_NAME));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return schema;
  }
}
