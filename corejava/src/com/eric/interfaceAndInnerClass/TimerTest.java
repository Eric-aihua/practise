package com.eric.interfaceAndInnerClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		Date d=new Date();
		System.out.println("the time is:"+d);
		Toolkit.getDefaultToolkit().beep();
	}
	public static void main(String[] args) {
		ActionListener al=new TimerTest();
		Timer t=new Timer(1000,al);
		t.start();
		System.out.println("stop timer!!!1");
		
		JOptionPane.showMessageDialog(null,"exit sysytem");
		System.exit(0);
	}

}
	