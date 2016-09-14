package com.cn.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoRunnable implements Runnable{
	private Socket s;
	private int count;
	public EchoRunnable(Socket s, int count) {
		super();
		this.s = s;
		this.count = count;
		
	}

	public void run() {
		try {
			InputStream is = s.getInputStream();
			Scanner sc=new Scanner(is);
			OutputStream os=s.getOutputStream();
			PrintWriter pw=new PrintWriter(os,true);
			pw.println("enter bye to exit:");
			boolean done=false;
			while(!done&&sc.hasNextLine()){
				String line=sc.nextLine();
				pw.println("echo:"+line+"  "+count);
				if(line.trim().equals("bye")){
					done=true;
				}
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
