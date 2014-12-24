package com.eric.io;

import java.io.File;
import java.io.RandomAccessFile;

import com.eric.reflect.IOEffectiveInterceptor;

public class UsingRandomAccessFile implements IOOperation {
	static final File	file	= new File("test.file");
	static int count=100;
	
	public void write() throws Exception {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < count; i++) {
			raf.writeInt(i);
		}
		for (int i = 0; i < count; i++) {
			raf.writeUTF("sunaihua");
		}
		raf.close();
	}
	
	public void read() throws Exception {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		for (int i = 0; i < count; i++) {
			System.out.println(raf.readInt());
		}
		for (int i = 0; i < count; i++) {
			System.out.println(raf.readUTF());
		}
		raf.close();
	}
	
	public static void main(String[] args) throws Exception {
		IOOperation ope = new UsingRandomAccessFile();
		IOOperation operation = (IOOperation) IOEffectiveInterceptor.newIOInstance(ope);
		operation.write();
		operation.read();
	}
}
