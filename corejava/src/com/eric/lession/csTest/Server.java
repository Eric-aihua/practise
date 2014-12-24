package com.eric.lession.csTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket sSocket = new ServerSocket(0003);
			while (true) {
				Socket socket = sSocket.accept();
				InetAddress ia = socket.getInetAddress();
				//System.out.println("用户的地址是" + ia);
				if (socket != null) {
					new Thread(new ServerRunnabel(socket)).start();
				} else {
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class ServerRunnabel implements Runnable {

	private int i = 0;
	private Socket socket;
	private File file;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ReadTestQuestion rtq;
	private boolean noticeTestTime = true;

	public ServerRunnabel(Socket socket) {
		super();
		rtq=new ReadTestQuestion();
		this.socket = socket;
		try {
			dos=new DataOutputStream(socket.getOutputStream());
			dis=new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			String request;
			try {
				request=dis.readUTF();
				System.out.println("request:"+request);/*server response */
				if(request.startsWith("列出考试文件")){
					File dir=new File(System.getProperty("user.dir")+"/src/com/eric/lession/csTest");
					FileName fileName=new FileName("txt");
					String fileNames[]=dir.list(fileName);
					for (int i = 0; i < fileNames.length; i++) {
						dos.writeUTF("考试文件"+fileNames[i]);
					}
					dos.writeUTF("已经全部列出！");
				}
				else if(request.startsWith("考试文件的名字:")){
					String fileName=request.substring(request.indexOf(":")+1);
					fileName=System.getProperty("user.dir")+"/src/com/eric/lession/csTest/"+fileName;
					rtq.setFileName(fileName);/*执行到这里*/
					rtq.setFinished(false);
					noticeTestTime=true;
				}
				else if(request.startsWith("读取下一题")){
					String content=rtq.getTestContent();
					System.out.println("service.nextques_content:"+content);
					dos.writeUTF("试题内容:"+content);
					
					if(rtq.isFinished()){
						//dos.writeUTF("考试结束");
					}
				}
				else if(request.startsWith("请通知考试界面考试用时")){
					if(noticeTestTime==true){
						long time=rtq.getTime();
						dos.writeUTF("考试用时:"+time+"秒");/*此时发送一个考试用时的信息，但是系统的第一阶段没有设计考试时间的问题，所以，先将起注释，否则会引发当点击下一题时会把时间给显示出来*/
						noticeTestTime=false;
					}
				}
				else if(request.startsWith("提交的答案:")){
					String answer=request.substring(request.indexOf(":")+1);
					System.out.println("server.90.else if(request.startsWith(提交的答案:"+answer);//
					rtq.setUserAnswer(answer);
				}
				else if(request.startsWith("查看得分")){
					int score=rtq.getScore();
					String message=rtq.getMessage();
					dos.writeUTF("我考试得分:"+score+"\n"+message);
					System.out.println("server request start with is 查看得分 --考试得分:"+score+"\n"+message);
				}
			} catch (IOException e) {
				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					//System.out.println("connection is break!");
				}
			}
		}
	}

}

class FileName implements FilenameFilter {

	private String str;

	public FileName(String str) {
		super();
		this.str = "." + str;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public boolean accept(File dir, String name) {
		return name.endsWith(str);
	}

}