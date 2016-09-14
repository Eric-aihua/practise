package com.eric.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class IoTest {
	public static void main(String[] args) {
		// try {
		// int i=System.in.read();//�Ӽ�������ֵ
		// InputStream in=null;
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
		System.out.println(System.getProperty("user.dir"));// ��õ�ǰ����Ŀ¼
		// ��ȡ�ļ��е���Ϣ
		File f = new File("src/com/eric/io/test.file");
		try {
			FileInputStream fis = new FileInputStream(f);
			FileReader fr = new FileReader(f);
			byte[] arr = new byte [(int) f.length()];
			char[] arr2 = new char [(int) f.length()];
			fis.read(arr);
			int num = fr.read(arr2);
			String str = new String(arr2, 0, num);// ��ȡ����
			
			for (int i = 0; i < arr.length; i++) {
				System.out.print((char) arr[i]);
			}
			System.out.println();
			for (int i = 0; i < arr2.length; i++) {
				System.out.print((char) arr[i]);
			}
			System.out.println();
			System.out.println(str);
			fr.close();
			
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
