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
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShowRecord extends JDialog implements ActionListener {
	private File grateFile;
	private JButton submit,clear;
	private JTextArea show;
	public ShowRecord(Memory frame, File grateFile) {
		super(frame,"memory top");
		this.grateFile=grateFile;
		show=new JTextArea(6,4);
		submit=new JButton("submit");
		submit.addActionListener(this);
		clear=new JButton("clear");
		clear.addActionListener(this);
		Container container=getContentPane();
		container.setLayout(new BorderLayout());
		container.add(new JScrollPane(show),BorderLayout.CENTER);
		JPanel p=new JPanel();
		p.add(submit);
		p.add(clear);
		container.add(p,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
				dispose();
			}
		});
		setBounds(100,100,310,160);
		setResizable(false);
		setVisible(false);
		setModal(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit){
			show.setText(null);
			FileInputStream fis;
			try {
				fis = new FileInputStream(grateFile);
				ObjectInputStream ois=new ObjectInputStream(fis);
				LinkedList list=(LinkedList)ois.readObject();
				ois.close();
				fis.close();
				sort(list);
				for (int i = 0; i < list.size(); i++) {
					People people=(People)list.get(i);
					show.append("\n"+people.getName()+" "+people.getTime());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==clear){
			FileInputStream fis;
			try {
				fis = new FileInputStream(grateFile);
				ObjectInputStream ois=new ObjectInputStream(fis);
				LinkedList list=(LinkedList)ois.readObject();
				list.clear();
				show.setText("warning: top has been clear!");
				ois.close();
				fis.close();
				FileOutputStream fos=new FileOutputStream(grateFile);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(list);
				oos.close();
				fos.close();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void sort(LinkedList list) {
		for (int i = 0; i < list.size()-1; i++) {
			for (int j = i+1; j < list.size(); j++) {
				if(((People)list.get(i)).getTime()>((People)list.get(j)).getTime()){
					People temp=(People)list.get(j);
					list.set(j, (People)list.get(i));
					list.set(i, temp);
				}
			}
		}
	}
	
}
