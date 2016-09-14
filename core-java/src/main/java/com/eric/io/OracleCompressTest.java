package com.eric.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class OracleCompressTest {
	private static String	   CON_URL	            = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String	   USER	                = "Ruegen";
	private static String	   PWD	                = "Ruegen";
	private static String	   CODE	                = "ISO-8859-1";
	private static int	       ID	                = 10;
	private static int	       appendtime	        = 30;
	
	public static void main(String[] args) throws Exception {
		String str = "abcdefghijklmnopqrst";
		// testCompress(str);
		String insert = "insert into COMPRESSTEST ( id, str ) values ( ? , ? )";
		String query = "select str from compresstest t where t.id=" + ID;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(CON_URL, USER, PWD);
			PreparedStatement pstmt = conn.prepareStatement(insert);
			String newstr = (String) compress(append(str));
			// System.out.println("####compressString:" + newstr);
			pstmt.setInt(1, ID);
			pstmt.setString(2, newstr);
			System.out.println(pstmt.executeUpdate());
			
			PreparedStatement pstmt2 = conn.prepareStatement(query);
			ResultSet rs = pstmt2.executeQuery();
			while (rs.next()) {
				String a = rs.getString("str");
				System.out.println("umCompress from DB:" + uncompress(a));
			}
			
			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void testCompress(String str) throws IOException {
		String append = append(str);
		System.out.println("before test:" + append);
		String test = compress(append);
		System.out.println("have test compressed:" + test);
		System.out.println("have test uncompressed:" + uncompress(test));
		
	}
	
	public static String compress(String str) throws IOException {
		System.out.println("before length:" + str.length());
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str.getBytes());
		gzip.close();
		System.out.println("after length:" + out.toString(CODE).length());
		return out.toString(CODE);
	}
	
	public static String uncompress(String str) throws IOException {
		System.out.println("umcompress  before:" + str);
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes(CODE));
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte [256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toString();
	}
	
	public static String append(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < appendtime; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
	
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
