package com.eric.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.util.Bytes;

public class OpentsdbClient {
	
	public static final String testTable="test";
	public static final String testFamily="t";
	public static void main(String[] args) throws Exception {
		BaseOperation.creatTable(testTable, new String[]{testFamily});
		generateColumn();
	}
	
	
	public static void generateColumn() throws Exception{
		for(int i=0;i<3600;i++){
			BaseOperation.addRecord(testTable, i+"", testFamily, "C"+i, i+"");
		}
	}
	
	public static void test() throws IOException{
		BaseOperation.getRecordByColFilter("tsdb-uid",Bytes.toHex(Bytes.toBytes(1000)));
		BaseOperation.getAllRecord("tsdb");
	}

}
