package com.eric.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting {
	public static final String	ROOTDIR	= "E:\\sourcecode\\corejava\\src\\com\\eric\\io\\";
	
	public static void main(String[] args) throws Exception {
		PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(ROOTDIR + "test.file"))));
		// set System.out point to out
		System.setOut(out);
		for (int i = 0; i < 10; i++) {
			// console content will be wrote in test.file
			System.out.println("write to PrintStream file:" + i);
		}
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(ROOTDIR + "Redirecting.java"));
		// set System.in point to in
		System.setIn(in);
		// use System.in to initialize BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = br.readLine()) != null)
			System.out.println(s);
		System.setErr(out);
		// String nullObj=null;
		// nullObj.concat("sdf");
		in.close();
		out.close();
		
	}
}