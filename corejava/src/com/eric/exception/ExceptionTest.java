package com.eric.exception;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ExceptionTest {
	public static void main(String[] args) {
		new ExceptionFrame();
	}
}

class ExceptionFrame extends JFrame {
	public ExceptionFrame() {
		setSize(500,500);
		add(new ExceptionBox());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class ExceptionBox extends Box {

	private JTextField text;
	private ButtonGroup group;
	private double[] a;
	public ExceptionBox() {
		super(BoxLayout.X_AXIS);
		group=new ButtonGroup();
		a=new double[10];
		text=new JTextField();
		add(text);
		addRadioButton("integer dive by zero",new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				a[1]=1/(a.length-a.length);
			}
				
		});
		addRadioButton("call null point", new ActionListener(){
			public void actionPerformed(ActionEvent e){
				e=null;
				System.out.println(e.getSource());
			}
		});
		
	}

	private void addRadioButton(String s, ActionListener listener) {
		JRadioButton jrb = new JRadioButton(s) {

			@Override
			protected void fireActionPerformed(ActionEvent event) {
				try {
					text.setText("NO Exception");
					super.fireActionPerformed(event);

				} catch (Exception e) {
					text.setText(e.toString());
				}
				;
			}
		};
		jrb.addActionListener(listener);
		add(jrb);
		group.add(jrb);
		
	}

}
