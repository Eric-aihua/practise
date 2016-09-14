package com.cn.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(8189);
//			InputStream is=s.getInputStream();
//			OutputStream os=s.getOutputStream();
//			PrintWriter pw=new PrintWriter(os,true);
//			Scanner sc=new Scanner(is);
//			pw.println("hello！enter bye to exit");
//			boolean done=false;
//			while(!done&&sc.hasNextLine()){
//				String line=sc.nextLine();
//				pw.println("--echo:"+line);
//				if(line.trim().equals("bye")){
//					done=true;
//				}
//			}
//			s.close();
			int count=0;
			while(true){
				Socket s=ss.accept();
				Runnable r=new EchoRunnable(s, count); 
				Thread t=new Thread(r);
				t.start();
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
