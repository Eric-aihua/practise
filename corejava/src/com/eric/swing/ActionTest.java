package com.eric.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionTest {
	public static void main(String[] args) {
		new ActionJFrame();
	}

}

class ActionJFrame extends JFrame {
	public ActionJFrame(){
		setSize(500, 500);
		add(new ActionJPanel());
		setVisible(true);
	}
}

class ActionJPanel extends JPanel {
	public ActionJPanel(){
		setSize(500,500);
		JButton yellow=new JButton(new ColorAction("yellow",Color.YELLOW));
		yellow.setIcon(new ImageIcon("src/com/eric/lession/testmemory/a4.gif"));
		add(yellow);
		add(new JButton(new ColorAction("blue",Color.blue)));
		add(new JButton(new ColorAction("green",Color.green)));
		
	}
	class ColorAction extends AbstractAction {

		public ColorAction(String name, Color c) {
			putValue(Action.NAME, name);
			putValue("color", c);
		}

		public void actionPerformed(ActionEvent e) {
			Color c = (Color) getValue("color");
			setBackground(c);
		}

	}

}