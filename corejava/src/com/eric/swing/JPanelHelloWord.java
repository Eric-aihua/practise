package com.eric.swing;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelHelloWord {
	
	public static void main(String[] args) {
		JFrameHelloword jf=new JFrameHelloword();
		jf.setVisible(true);
	}

}
class JFrameHelloword extends JFrame{
	public JFrameHelloword(){
		setSize(500,500);
		setTitle("helloword");
		PanelHelloword jp=new PanelHelloword();
		jp.setBackground(Color.blue);
		add(jp);
	}
}
class PanelHelloword extends JPanel{
	public void paintComponent(Graphics g){
		
		super.paintComponents(g);
		setBackground(Color.BLUE);
		Graphics2D g2=(Graphics2D)g;
		g2.setPaint(Color.red);
		float left=100;
		float topy=100;
		float width=200;
		float heigh=150;
		
		Rectangle2D rec=new Rectangle2D.Float(left,topy,width,heigh);
		g2.draw(rec);
		Ellipse2D ellipse2D=new Ellipse2D.Float();
		ellipse2D.setFrame(rec);
		g2.draw(ellipse2D);
		
		g2.draw(new Line2D.Float(left,topy,width,heigh));
	}
}
