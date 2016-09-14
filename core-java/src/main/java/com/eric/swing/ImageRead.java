package com.eric.swing;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageRead {
	public static void main(String[] args) {
		ImageFrame ifr=new ImageFrame();
		ifr.init();
	}

}
class ImageFrame extends JFrame{
	public ImageFrame(){}
	public void init(){
		setSize(500,500);
		add(new ImagePanel());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
class ImagePanel extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Image image;
		try {
			image = ImageIO.read(new File("/wind.gif"));
			System.out.println(image+"*******");
			g.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
