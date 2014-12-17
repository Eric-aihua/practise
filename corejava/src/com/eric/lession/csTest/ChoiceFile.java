package com.eric.lession.csTest;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ChoiceFile extends JPanel implements ItemListener,ActionListener,Runnable{

	private JButton getExFile;
	private Choice list;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	private Thread thread;
	private boolean success=false;
	private boolean isSelected;
	private int n;
	public ChoiceFile(){
		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		getExFile=new JButton("领试卷");
		panel.add(getExFile,BorderLayout.WEST);
		getExFile.addActionListener(this);
		add(getExFile);
		list=new Choice();
		list.setSize(100, 20);
		list.add("***");
		list.addItemListener(this);
		panel.add(list,BorderLayout.EAST);
		add(panel);
		thread=new Thread(this);
		validate();
	}
	public void itemStateChanged(ItemEvent e) {
		n=list.getItemCount();
		if(n>=1&&isSelected==true){/* isSelected==true*/
			String listItem=list.getSelectedItem();
			try{
				dos.writeUTF("考试文件的名字:"+listItem);
				dos.writeUTF("请通知考试界面考试用时:");
				dos.writeUTF("读取下一题");
				success=true;
			}catch(IOException ee){
				
			}
		}
	}
	public String getSelectedFileName(){
		return list.getSelectedItem();
	}
	public void setSocketConnection(Socket socket,DataInputStream dis,DataOutputStream dos){
		this.socket=socket;
		this.dis=dis;
		this.dos=dos;
		thread.start();
		getExFile.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			dos.writeUTF("列出考试文件");
		} catch (IOException e1) {
			success=false;
			e1.printStackTrace();
			
		}
	}
	public void run() {
		String s;
		list.removeAll();
		while(true){
		try {
			s=dis.readUTF();//该方法会阻碍线程，知道服务器端发来相应的信息
			if(s.startsWith("考试文件")){
				String listItem=s.substring(s.indexOf("件")+1);
				list.add(listItem);
				isSelected=true;
			}
			else if(s.startsWith("已")){
				success=true;
				break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	public boolean getSuccess(){
		n=list.getItemCount();
		if(n>0&&success==true){
			return true;
		}
		else{
			return false;
		}
	}
	public DataInputStream getDis() {
		return dis;
	}
	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
}
