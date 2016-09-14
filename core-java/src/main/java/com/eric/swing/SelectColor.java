package com.eric.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SelectColor {
	public static void main(String[] args) {
		ColorFrame cf=new ColorFrame();
	}
}
class ColorFrame extends JFrame{
	public ColorFrame(){
		setSize(500, 500);
		//add(new ColorPanel());
		add(new platPlant());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
class ColorPanel extends JPanel{
	public ColorPanel(){
		JButton yellow=new JButton("yellow");
		JButton blue=new JButton("blue");
		JButton green=new JButton("green");
		
		ColorAction actionYellow=new ColorAction(Color.YELLOW);
		ColorAction actionBlue=new ColorAction(Color.BLUE);
		ColorAction actionGreen=new ColorAction(Color.GREEN);
		
		yellow.addActionListener(actionYellow);
		blue.addActionListener(actionBlue);
		green.addActionListener(actionGreen);
		
		
		add(yellow);
		add(blue);
		add(green);
		
		setSize(500, 500);
	}
	class ColorAction implements ActionListener{

		private Color color;
		public ColorAction(Color c){
			this.color=c;
		}
		public void actionPerformed(ActionEvent e) {
			setBackground(color);
		}
		
	}
}
class platPlant extends JPanel{
	public platPlant(){
		UIManager.LookAndFeelInfo[] us=UIManager.getInstalledLookAndFeels();
		for (int i = 0; i < us.length; i++) {
			makeButton(us[i].getName(),us[i].getClassName());
		}
		
	}
	public void makeButton(String name,final String palfName){
		JButton button=new JButton(name);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(palfName);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(platPlant.this);
			}
			
		});
			
		add(button);
		}
		
}
