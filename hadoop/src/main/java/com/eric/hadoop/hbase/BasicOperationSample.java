package com.eric.hadoop.hbase;

import java.io.IOException;
import java.io.InterruptedIOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class BasicOperationSample {
  private static final String TABLE_NAME = "blog";
  private static Configuration configuration = HBaseConfiguration.create();

  public static void main(String[] args) {
    try {
      insertRecords();
    } catch (MasterNotRunningException e) {
      e.printStackTrace();
    } catch (ZooKeeperConnectionException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert Records
   * 
   * @param configuration
   * @throws MasterNotRunningException
   * @throws ZooKeeperConnectionException
   * @throws IOException
   * @throws InterruptedIOException
   * @throws RetriesExhaustedWithDetailsException
   */
  private static void insertRecords() throws MasterNotRunningException,
      ZooKeeperConnectionException, IOException, InterruptedIOException,
      RetriesExhaustedWithDetailsException {
    HTable table;
    table = createTable();
    /** =========插入数据========= */
    Put put = new Put(Bytes.toBytes("1"));
    put.add(Bytes.toBytes("article"), Bytes.toBytes("title"), Bytes.toBytes("Head First HBase"));
    put.add(
        Bytes.toBytes("article"),
        Bytes.toBytes("content"),
        Bytes
            .toBytes("HBase is the Hadoop database. Use it when you need random, realtime read/write access to your Big Data."));
    put.add(Bytes.toBytes("article"), Bytes.toBytes("tags"), Bytes.toBytes("Hadoop,HBase,NoSQL"));
    put.add(Bytes.toBytes("author"), Bytes.toBytes("name"), Bytes.toBytes("hujinjun"));
    put.add(Bytes.toBytes("author"), Bytes.toBytes("nickname"), Bytes.toBytes("一叶渡江"));
    table.put(put);
  }

  /**
   * Retritval Records from table
   * 
   * @param tableName
   * @throws IOException
   */
  private static void retritvalAllRecord(String tableName) throws IOException {
    HTablePool pool = new HTablePool(configuration, 1000);
    HTable table = (HTable) pool.getTable(tableName);
    ResultScanner rs = table.getScanner(new Scan());
    for (Result result : rs) {
      System.out.println(result.getRow());
      for (KeyValue keyValue : result.raw()) {
        System.out.println("Family:" + keyValue.getKeyString() + "\tValue:" + keyValue.getValue());
      }
    }
  }

  /**
   * Delete table by table name
   * 
   * @param tableName
   * @throws MasterNotRunningException
   * @throws ZooKeeperConnectionException
   * @throws IOException
   */
  private static void deleteTable(String tableName) throws MasterNotRunningException,
      ZooKeeperConnectionException, IOException {
    HBaseAdmin admin = new HBaseAdmin(configuration);
    admin.disableTable(tableName);
    admin.deleteTable(tableName);
    System.out.println("Delete Table Successful");
  }

  /**
   * Delete a row from a table
   * 
   * @param tableName
   * @param rowkey
   * @throws IOException
   */
  private static void deleteRow(String tableName, String rowkey) throws IOException {
    HTable table = new HTable(configuration, tableName);
    Delete delete = new Delete(rowkey.getBytes());
    table.delete(delete);
    System.out.println("Delete Row Successful");
  }

  /**
   * Create Database
   * 
   * @param configuration
   * @return
   * @throws MasterNotRunningException
   * @throws ZooKeeperConnectionException
   * @throws IOException
   */
  private static HTable createTable() throws MasterNotRunningException,
      ZooKeeperConnectionException, IOException {
    HBaseAdmin admin = new HBaseAdmin(configuration);
    HTableDescriptor desc = new HTableDescriptor(TABLE_NAME);
    HTable table = new HTable(configuration, desc.getName());
    desc.addFamily(new HColumnDescriptor("article"));
    desc.addFamily(new HColumnDescriptor("author"));
    admin.createTable(desc);
    return table;
  }

}
