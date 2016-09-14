package com.eric.jvm.remoteexecute;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class HackSystem {
	static{
		try {
			baos=new FileOutputStream(new File("/home/eric/output.log"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static FileOutputStream baos ;
	public final static PrintStream out = new PrintStream(baos);
	public final static PrintStream error = out;

	public static String getBufferString() {
		return baos.toString();
	}

	public static void clearBuffer() throws IOException {
		baos.flush();
	}
}
