package com.eric.interfaceAndInnerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class InnerClassTest {
	public static void main(String[] args) {
		TalkClock tc=new TalkClock();
		tc.setInterval(1000);
		tc.start();
		
		TalkClock.Inner ti=tc.new Inner();
		ti.setName("eric");
		System.out.println(ti.getName());
		System.out.println(ti.getClass().getSimpleName());
		
		//�����ڲ����static final����
		System.out.println(TalkClock.Inner.s);
		
		System.out.println(TalkClock.StaticInnerClass.s);
		
	}
}
class TalkClock{
	private int interval;
	private boolean beep;
	private class TimerPrint implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Date d=new Date();
			System.out.println("the time is:"+d);
			if(beep)
			Toolkit.getDefaultToolkit().beep();
			
		}
		
	}
	public void start(){
		ActionListener al=new TimerPrint();
		Timer t=new Timer(interval,al);
		t.start();
		JOptionPane.showMessageDialog(null,"exit system!");
		System.exit(0);
	}
	public  class Inner{
		private String name;
		public static final String s="hello";
		public Inner(){};
		public String getName(){
			return name;
		}
		public void prin(){
			System.out.println(interval);
		}
		public void setName(String name){
			this.name=name;
		}
	}
	 static class StaticInnerClass{
		public static final String s="hello";
//		public void prin(){
//			System.out.println(interval);
//		}
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public boolean isBeep() {
		return beep;
	}
	public void setBeep(boolean beep) {
		this.beep = beep;
	}
}