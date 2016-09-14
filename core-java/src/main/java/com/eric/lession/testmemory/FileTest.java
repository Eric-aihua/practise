package com.eric.lession.testmemory;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileTest {
	public static void main(String[] args) {
		File gradeFile=new File("primaryTop.txt");
		FileInputStream fis;
		try {
			fis = new FileInputStream(gradeFile);
			System.out.println(fis);
			ObjectInputStream ois=new ObjectInputStream(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
