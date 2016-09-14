package com.eric.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderTest {
	public static void main(String[] args) {
		try {
			BufferedReader br=new BufferedReader(new FileReader("src/com/eric/io/test.file"));
			String line;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
