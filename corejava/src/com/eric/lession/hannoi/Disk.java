package com.eric.lession.hannoi;

import java.awt.Color;

import javax.swing.JButton;

public class Disk extends JButton {
	private int num;
	private boolean isPressed;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean isPressed() {
		return isPressed;
	}
	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	public Disk(int num,HannoiTower ht){
		this.num=num;
		setBackground(Color.blue);
		addMouseListener(ht);
		addMouseMotionListener(ht);
	}
	
}
