package com.eric.lession.testmemory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Memory extends JFrame implements ActionListener{
	private JMenuBar bar;
	private JMenu menu;
	private JMenuItem primary,intermediate,advanced,primaryTop,intermediateTop,advancedTop;
	private Block[] block;
	private ImageIcon icon[];
	private MemoryTestPane memoryTestPane;
	private File primaryFile;
	private File integermediateFile;
	private File advancedFile;
	private File gradeFile;
	private LinkedList scoreList;
	private ShowRecord showRecordDialog;
	private int m=5,n=6;
	private int inconNum=0;
	private Container container;
	private JTextField tipLable;
	public Memory(){
		primaryFile=new File("src/com/eric/lession/testmemory/primaryTop");
		integermediateFile=new File("src/com/eric/lession/testmemory/intermediateTop");
		advancedFile=new File("src/com/eric/lession/testmemory/advancedTop");
		block=new Block[m*n];
		inconNum=m;
		icon=new ImageIcon[inconNum];
		bar=new JMenuBar();
		menu=new JMenu("TEST MEMORY");
		primary=new JMenuItem("PRIMARY");
		intermediate=new JMenuItem("INTERMEDIATE");
		advanced=new JMenuItem("ADVANCE");
		primaryTop=new JMenuItem("PTOP");
		intermediateTop=new JMenuItem("ITOP");
		advancedTop=new JMenuItem("ATOP");
		primary.addActionListener(this);
		intermediate.addActionListener(this);
		advanced.addActionListener(this);
		primaryTop.addActionListener(this);
		intermediateTop.addActionListener(this);
		advancedTop.addActionListener(this);
		scoreList=new LinkedList();
		for (int i = 0; i < icon.length; i++) {
			icon[i]=new ImageIcon("src/com/eric/lession/testmemory/a"+i+".gif");
		}
		for (int i = 0; i < block.length; i++) {
			block[i]=new Block();
			block[i].setCloseIcon(new ImageIcon("src/com/eric/lession/testmemory/close.gif"));
		}
		
		menu.add(primary);
		menu.add(intermediate);
		menu.add(advanced);
		menu.add(primaryTop);
		menu.add(intermediateTop);
		menu.add(advancedTop);
		bar.add(menu);
		setJMenuBar(bar);
		
		if(!primaryFile.exists()){
			try {
				FileOutputStream fos=new FileOutputStream(primaryFile);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(scoreList);
				oos.close();
				fos.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!integermediateFile.exists()){
			try {
				FileOutputStream fos=new FileOutputStream(integermediateFile);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(scoreList);
				oos.close();
				fos.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!advancedFile.exists()){
			try {
				FileOutputStream fos=new FileOutputStream(advancedFile);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				oos.writeObject(scoreList);
				oos.close();
				fos.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		gradeFile=primaryFile;
		container=getContentPane();
		memoryTestPane=new MemoryTestPane(block,icon,m,n,gradeFile);
		tipLable=new JTextField("初级，你需要找出6个相同的方块");
		tipLable.setEditable(false);
		tipLable.setForeground(Color.red);
		setLayout(new BorderLayout());
		add(tipLable,BorderLayout.SOUTH);
		add(memoryTestPane,BorderLayout.CENTER);
		container.validate();
		setBounds(100, 100, 300, 260);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500,500);
		setLocation(300, 300);
		validate();
		
	}
	
	public void setMemoryLevel(int width,int heigh,File f){
		m=width;
		n=heigh;
		inconNum=m;
		gradeFile=f;
		block=new Block[m*n];
		icon=new ImageIcon[inconNum];
		for (int i = 0; i < icon.length; i++) {
			icon[i]=new ImageIcon("src/com/eric/lession/testmemory/a"+i+".gif");
		}
		for (int i = 0; i < block.length; i++) {
			block[i]=new Block();
			block[i].setCloseIcon(new ImageIcon("src/com/eric/lession/testmemory/close.gif"));
		}
		
		memoryTestPane=new MemoryTestPane(block,icon,m,n,gradeFile);
		container.removeAll();
		container.add(memoryTestPane,BorderLayout.CENTER);
		container.add(tipLable,BorderLayout.SOUTH);
		container.validate();
		this.validate();
		
	}
	public static void main(String[] args) {
		new Memory();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==primary){
			setMemoryLevel(5, 6, primaryFile);
			setSize(500,500);
			setLocation(300, 300);
			this.validate();
			tipLable.setText("初级：你需找找出"+n+"个相同图标的方块" );
		}
		if(e.getSource()==intermediate){
			setMemoryLevel(6, 7, integermediateFile);
			setSize(500,500);
			setLocation(300, 300);
			this.validate();
			tipLable.setText("中级：你需找找出"+n+"个相同图标的方块");
		}
		if(e.getSource()==advanced){
			setMemoryLevel(7, 8, advancedFile);
			setSize(500,500);
			setLocation(300, 300);
			this.validate();
			tipLable.setText("高级：你需找找出"+n+"个相同图标的方块");
		}
		if(e.getSource()==primaryTop){
			showRecordDialog=new ShowRecord(this,primaryFile);
			showRecordDialog.setVisible(true);
		}
		if(e.getSource()==intermediateTop){
			showRecordDialog=new ShowRecord(this,integermediateFile);
			showRecordDialog.setVisible(true);
		}
		if(e.getSource()==advancedTop){
			showRecordDialog=new ShowRecord(this,advancedFile);
			showRecordDialog.setVisible(true);
		}
	}
	
}
