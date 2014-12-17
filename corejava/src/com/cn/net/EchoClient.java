package com.cn.net;

import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) {
		try {
			Socket s=new Socket("10.211.3.181",8189);
			InputStream is=s.getInputStream();
			Scanner sc=new Scanner(is);
			while(sc.hasNextLine()){
				String line=sc.nextLine();
				System.out.println(line);
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
