package com.eric.lession.testmemory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Record extends JFrame implements ActionListener {
	private JTextField yourName,label;
	private int time=0;
	private JButton submit,cancel;
	private File grateFile;
	public Record(File grateFile){
		super("record your grate to:"+grateFile.getName());
		this.grateFile=grateFile;
		submit=new JButton("submit");
		submit.addActionListener(this);
		cancel=new JButton("cancel");
		cancel.addActionListener(this);
		yourName=new JTextField(10);
		yourName.setText("anmous");
		Container container=getContentPane();
		container.setLayout(new BorderLayout());
		label=new JTextField("insert your name,it's will put in :"+grateFile.getName());
		label.setEditable(false);
		container.add(yourName,BorderLayout.NORTH);
		container.add(label,BorderLayout.CENTER);
		JPanel p=new JPanel();
		p.add(submit);
		p.add(cancel);
		container.add(p,BorderLayout.SOUTH);
		container.validate();
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
				dispose();
			}
		});
		setBounds(100,100,310,160);
		setLocation(300, 300);
		setResizable(false);
		setVisible(false);
		validate();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit){
			try {
				FileInputStream fis=new FileInputStream(grateFile);
				ObjectInputStream ois=new ObjectInputStream(fis);
				LinkedList list=(LinkedList)ois.readObject();
				ois.close();
				fis.close();
				People people=new People(yourName.getText(),time);
				list.add(people);
				FileOutputStream fos=new FileOutputStream(grateFile);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.close();
				fos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==cancel){
			setVisible(false);
			dispose();
		}
			
	}

	public void setGrateFile(File grateFile) {
		this.grateFile = grateFile;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
