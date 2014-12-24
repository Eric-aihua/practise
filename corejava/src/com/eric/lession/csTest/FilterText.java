package com.eric.lession.csTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FilterText {
	public static void main(String[] args) {
		File dir=new File(System.getProperty("user.dir")+"/src/com/eric/lession/csTest");
		FileName fileName=new FileName("txt");
		String fileNames[]=dir.list(fileName);
		for (int i = 0; i < fileNames.length; i++) {
			//dos.writeUTF("考试文件"+fileNames[i]);
			//System.out.println("file name"+fileNames[i]);/*检查是否像dos里写信息*/
		}
		String s="考试文件的名字:Ex.txt";
		System.out.println(s.substring(s.indexOf("件")+1));
		String ss="已经全部列出";
		System.out.println("ss.start with:"+ss.startsWith("已"));
		System.out.println("试题内容".startsWith("试"));
		try {
			String s2="";
			BufferedReader br=new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"/src/com/eric/lession/csTest/bEx.txt")));
			while(( s2=br.readLine())!=null){
				String s3=new String(s2.getBytes("gbk"),"utf-8");/*对代码进行编码方式转化，用编辑时的方式来提取，用新的编码方式来生成*/
				//System.out.println(s3);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end".startsWith("e"));
		
		System.out.println("***************************************************************");
	String s3="考试得分:1";
	if(s3.startsWith("考试得分")){
	System.out.println(s3.substring(s3.indexOf(":")+1));
		}
	}
}
