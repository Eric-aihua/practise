package com.eric.lession.testmemory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Block extends JButton implements ActionListener{
	
	private ImageIcon closeIcon;
	private ImageIcon rollIcon;
	public ImageIcon getCloseIcon() {
		return closeIcon;
	}

	public ImageIcon getRollIcon() {
		return rollIcon;
	}

	public void setCloseIcon(ImageIcon imageIcon) {
		closeIcon=imageIcon;
	}

	public void setRollIcon(ImageIcon imageIcon) {
		rollIcon=imageIcon;
	}

	public void actionPerformed(ActionEvent e) {
		this.setIcon(rollIcon);
	}
	
}
