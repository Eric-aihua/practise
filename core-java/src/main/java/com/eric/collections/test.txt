package com.eric.lession.testmemory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MemoryTestPane extends JPanel implements ActionListener {

	private RandomSetIcon setIcon;
	private Block blocks[];
	private ImageIcon icons[];
	private LinkedList listIcon;
	private LinkedList listBlock;
	private int line;
	private int column;
	private int success=0;
	private int time=0;
	private Timer timer;
	private JTextField showTime;
	private File gradeFile;
	private boolean runTimmer=false;
	
	public MemoryTestPane(Block[] blocks, ImageIcon[] icon, int m, int n,
			File gradeFile) {
			this.blocks=blocks;
			this.icons=icon;
			line=m;
			column=n;
			this.gradeFile=gradeFile;
			listIcon=new LinkedList();
			listBlock=new LinkedList();
			setIcon=new RandomSetIcon();
			showTime=new JTextField();
			showTime.setEditable(false);
			showTime.setForeground(Color.red);
			JPanel center=new JPanel();
			JPanel south=new JPanel();
			south.add(showTime);
			center.setLayout(new GridLayout(line,column));
			setIcon.setRandomIcon(blocks, icon);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i].addActionListener(this);
				blocks[i].setIcon(blocks[i].getCloseIcon());
				center.add(blocks[i]);
			}
			setLayout(new BorderLayout());/*要放在add的前面*/
			add(south,BorderLayout.SOUTH);
			add(center,BorderLayout.CENTER);
			timer=new Timer(1000,this);
			runTimmer=false;
			setVisible(true);
			validate();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof Block){
			Block block=(Block)e.getSource();
			ImageIcon rollIcon=block.getRollIcon();
			block.setIcon(rollIcon);
			if(listIcon.size()==0){
				listIcon.add(rollIcon);
				listBlock.add(block);
				success=1;
			}
			else{
				ImageIcon temp=(ImageIcon)listIcon.getLast();
				if(temp==rollIcon&&!(listBlock.contains(block))){
					success=success+1;
					listIcon.add(rollIcon);
					listBlock.add(block);
					if(success==column){
						for (int i = 0; i < blocks.length; i++) {
							blocks[i].setEnabled(false);
						}
						for (int i = 0; i < listBlock.size(); i++) {
							Block b=(Block)listBlock.get(i);
							b.setDisabledIcon(b.getRollIcon());
						}
						timer.stop();
						Record record=new Record(gradeFile);
						record.setTime(time);
						record.setVisible(true);
					}
				}
				if(temp!=rollIcon){
					listIcon.clear();
					listBlock.clear();
					listIcon.add(rollIcon);
					listBlock.add(block);
					success=1;
					for (int i = 0; i < blocks.length; i++) {
						if(block!=blocks[i]){
							blocks[i].setIcon(blocks[i].getCloseIcon());
						}
					}
				}
			}
			if(runTimmer==false){
				time=0;
				timer.start();
				runTimmer=true;
			}
		}
		if(e.getSource()==timer){
			time=time+1;
			showTime.setText("you use "+time+" second!");
		}
	}

}
