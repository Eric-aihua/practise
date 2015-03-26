package com.eric.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.util.Bytes;

public class OpentsdbClient {
	public static void main(String[] args) throws IOException {
		BaseOperation.getRecordByColFilter("tsdb-uid",Bytes.toHex(Bytes.toBytes(1000)));
		//BaseOperation.getAllRecord("tsdb");
	}

}
