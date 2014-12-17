package com.eric.lession.csTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class ReadTestQuestion {
	private String fileName;
	private String correctAnswer;
	private String testContent;
	private String userAnswer="";
	private int score;
	private long time;
	private boolean finished=false;
	private File f;
	private FileReader in;
	private BufferedReader br;
	private String message;

	public String getMessage() {
		int ul=userAnswer.length();
		int cl=correctAnswer.length();
		int min=Math.min(ul, cl);
		message="正确答案为:"+correctAnswer.substring(0, min)+"\n"+"你的答案为:"+userAnswer;
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {/*选择文件的初始化工作*/
		fileName = fileName;
		score = 0;
		correctAnswer = "";
		try {
			if (in != null && br != null) {
				in.close();
				br.close();
			}
			f=new File(fileName);
			in=new FileReader(f);
			br=new BufferedReader(in);
			correctAnswer=br.readLine().trim();
			String temp=br.readLine().trim();
			StringTokenizer st=new StringTokenizer(temp,":");
			int hour=Integer.parseInt(st.nextToken());
			int minute=Integer.parseInt(st.nextToken());
			int second=Integer.parseInt(st.nextToken());
			time=hour*3600+minute*60+second;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("file init");
			testContent="请选择试题！";
		}
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getTestContent() {/*获取试题内容*/
		StringBuffer temp=new StringBuffer();
		String s;
		if(br!=null){
			try{
			while(!finished&&(s=br.readLine())!=null){
				String s3=new String(s.getBytes("gbk"),"utf-8");
				if(s.startsWith("**"))
					break;
				if(s3.startsWith("end")){
					in.close();
					br.close();
					finished=true;
				}
				temp.append("\n "+s3);
				testContent=temp.toString();
				
			}
			}catch(Exception e){
				e.printStackTrace();
				testContent="读取内容出错，考试结束！";
			}
		}
		else{
			testContent="请选择试题!!";
		}
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = this.userAnswer+userAnswer;
		System.out.println("readTestQuestion.setUserAnswer.userAnswer"+this.userAnswer);
	}

	public int getScore() {
		score=0;
		int ul=userAnswer.length();
		int cl=correctAnswer.length();
		System.out.println("readTestQuestion  user answer:"+userAnswer+"  correctAnswer: "+correctAnswer);
		int min=Math.min(ul, cl);
		for (int i = 0; i < min; i++) {
			try{
			if(userAnswer.charAt(i)==correctAnswer.charAt(i)){
				score++;
			}
			}
			catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
				System.out.println("计算分数是出错！");
				score=0;
			}
		}
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public FileReader getIn() {
		return in;
	}

	public void setIn(FileReader in) {
		this.in = in;
	}
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
