package com.eric.io;

import java.io.InputStream;

public class ReadCurrentDirImage {
	public static void main(String[] args) {
		new ReadCurrentDirImage().read();
	}
	
	public void read() {
		try {
			InputStream in = getClass().getResourceAsStream("default.jpg");
			System.out.println(in.available());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
