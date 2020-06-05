package com.eric.avro;

import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.joda.time.Instant;

import java.io.File;
import java.io.IOException;

public class AvroSample {
    public static void main(String[] args) throws IOException {
//        GenericRecord record  = new GenericData.Record(MyRecord.getClassSchema());;
        GenericRecord record  = new GenericData.Record(MyRecord.getClassSchema());
        Instant millis = Instant.now();
        //Insert data according to schema
        record.put("timestamp_with_logical_type", millis);
        record.put("timestamp_no_logical_type", 1000);

        DataFileWriter<GenericRecord> dataFileWriter = null;
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>();
        dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.setCodec(CodecFactory.snappyCodec());
        dataFileWriter.setFlushOnEveryBlock(true);
        dataFileWriter.setSyncInterval(32);
        try {
            dataFileWriter.create(MyRecord.getClassSchema(),new File("./demo.avro"));
            dataFileWriter.append(record);
            dataFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO 反序列
        DatumReader<MyRecord> productDatumReader = new SpecificDatumReader<MyRecord>(MyRecord.class);
        DataFileReader<MyRecord> productDataFileReader = new DataFileReader<MyRecord>(new File("./demo.avro") , productDatumReader);
        MyRecord pro_reader = null;
        while (productDataFileReader.hasNext()){
            pro_reader = productDataFileReader.next();
            System.out.println(pro_reader);
        }
    }
}
