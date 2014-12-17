package com.eric.io;

import java.io.IOException;
import java.io.InputStream;

public class test {
	public static void main(String[] args) {
		try {
			int i=System.in.read();
			InputStream in=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.getProperty("user.dir"));
	}
}
