package com.eric.io;

import java.io.PrintWriter;

public class ChangeSystemOut {
	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out, false);
		pw.write("hello world!\n");
		pw.write("sunaihua");
		// must class close at the end of method
		pw.close();
	}
	
}
