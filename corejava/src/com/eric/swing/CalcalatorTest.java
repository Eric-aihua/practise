package com.eric.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalcalatorTest {
	public static void main(String[] args) {
		new CalcalatorJFrame();
	}

}
class CalcalatorJFrame extends JFrame{
	public CalcalatorJFrame(){
		setSize(500,500);
		add(new CalcalatorJPanel());
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 200);
	}
	
}
class CalcalatorJPanel extends JPanel{
	private JButton command;
	private JPanel panel;
	private insertAction ia;
	private commandAction ca;
	private int temp;
	static String opetare;
	public CalcalatorJPanel(){
		ia=new insertAction();
		ca=new commandAction();
		setLayout(new BorderLayout());
		command=new JButton();
		command.setActionCommand("");
		add(command,BorderLayout.NORTH);
		String[] number={"1","2","3","4","5","6","7","8","9","0"};
		String[] commandString={"+","-","*","/",".","="};
		panel=new JPanel();
		panel.setLayout(new GridLayout(4,4));
		for (int i = 0; i < number.length; i++) {
			addButton(number[i], ia);
		}
		for (int i = 0; i < commandString.length; i++) {
			addButton(commandString[i], ca);
		}
		add(panel,BorderLayout.CENTER);
	}
	public void addButton(String name,ActionListener al){
		JButton jb=new JButton(name);
		jb.addActionListener(al);
		panel.add(jb);
		
	}
	class insertAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String number=e.getActionCommand();
			if(opetare==null||temp==0)
			command.setText(command.getText()+number);
			else
			{
				int result=getRe(temp,opetare,Integer.valueOf(e.getActionCommand()));
				command.setText(Integer.toString(result));
			}
		}
		
	}
	class commandAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			temp=Integer.valueOf(command.getText());
			command.setText("");
			opetare=e.getActionCommand();
			
			
		}
		
	}
	public static int getRe(int temp,String operate,int number){
		int result=temp;
		if(operate.equals("+")){
			result=result+number;
		}
		if(operate.equals("-")){
			result=result-number;
		}
		if(operate.equals("*")){
			result=result*number;
		}
		if(operate.equals("/")){
			result=result/number;
		}
		if(operate.equals("=")){
			return result;
		}
		return result;
	}
}
