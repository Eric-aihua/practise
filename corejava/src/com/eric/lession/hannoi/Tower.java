package com.eric.lession.hannoi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Tower extends JFrame implements ActionListener,Runnable{
	private HannoiTower tower;
	private JButton reBegin,autoRun;
	private char[] towerName;
	private int towerNum,towerWidth,towerHeigh;
	private Thread thread;
	private JTextArea textArea;
	
	public Tower(){
		thread=new Thread(this);
		towerNum=5;
		towerName=new char[]{'A','B','C'};
		towerWidth=80;
		towerHeigh=18;
		textArea=new JTextArea(12,12);
		textArea.setText("");
		tower=new HannoiTower(towerNum,towerWidth,towerHeigh,towerName,textArea);
		reBegin=new JButton("reBegin");
		autoRun=new JButton("autoRun");
		reBegin.addActionListener(this);
		autoRun.addActionListener(this);
		JPanel p=new JPanel();
		p.add(reBegin);
		p.add(autoRun);
		p.validate();
		add(p,BorderLayout.SOUTH);
		add(tower,BorderLayout.CENTER);
		add(textArea,BorderLayout.EAST);
		setTitle("HANNOI TOWER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		setSize(670, 540);
		setLocation(300, 300);
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reBegin){
			if(!(thread.isAlive())){
				tower=new HannoiTower(towerNum,towerWidth,towerHeigh,towerName,textArea);
				add(tower,BorderLayout.CENTER);
			}
			else{
				System.out.println("正在运行，请稍等！");
			}
		}
		if(e.getSource()==autoRun){
			if(!(thread.isAlive())){
				thread=new Thread(this);
			}
			else{
				thread.start();
			}
		}
	}

	public void run() {
		this.remove(tower);
		textArea.setText("");
		tower=new HannoiTower(towerNum,towerWidth,towerHeigh,towerName,textArea);
	
		add(tower,BorderLayout.CENTER);
		tower.autoMove(towerNum,towerName[0],towerName[1],towerName[2]);
		
	}
	public static void main(String[] args) {
		new Tower();
	}
	
}
