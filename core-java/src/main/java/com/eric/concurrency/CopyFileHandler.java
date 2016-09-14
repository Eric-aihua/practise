package com.eric.concurrency;

import java.io.IOException;

//117117
class CopyFileHandler implements Runnable {
	private String	src;
	private String	dest;
	
	public CopyFileHandler(String src, String dest) {
		super();
		this.src = src;
		this.dest = dest;
	}
	
	public void run() {
		try {
			CopyFileByMultiConcurrency.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}