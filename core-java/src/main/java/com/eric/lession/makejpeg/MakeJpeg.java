package com.eric.lession.makejpeg;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class MakeJpeg {
	public static void main(String[] args) {
		new Make();
	}
}
class Point{
	private int x;
	private int y;
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
class Make extends Canvas implements ActionListener,MouseMotionListener,MouseListener{
	private int x=-1;
	private int y=-1,eraserNotice=0,clearNotice=0;
	private Vector v=null;
	private int n=1;
	private Graphics2D g2;
	private BufferedImage image;
	private JFrame window;
	private JButton save,colorPanel,eraser,clear,pan,prScreen,drawShape,open;
	private Color panColor;
	private JPanel pCenter,pSouth,pNorth;
	
	public Make(){
		save=new JButton("save");
		colorPanel=new JButton("colorPanel");
		eraser=new JButton("eraser");
		clear=new JButton("clear");
		pan=new JButton("pan");
		prScreen=new JButton("prScreen");
		drawShape=new JButton("drawShape");
		open=new JButton("open");
		
		open.addActionListener(this);
		save.addActionListener(this);
		colorPanel.addActionListener(this);
		eraser.addActionListener(this);
		clear.addActionListener(this);
		pan.addActionListener(this);
		prScreen.addActionListener(this);
		drawShape.addActionListener(this);
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		panColor=new Color(0,0,0);
		v=new Vector();
		window=new JFrame();
		image=new BufferedImage(210,210,BufferedImage.TYPE_INT_RGB);
		pCenter=new JPanel();
		pSouth=new JPanel();
		pNorth=new JPanel();
		
		setBackground(Color.white);
		g2=image.createGraphics();
		Rectangle2D rec=new Rectangle2D.Double(0,0,210,210);
		g2.setColor(getBackground());
		g2.fill(rec);
		pCenter.setLayout(null);
		pCenter.add(this);
		pCenter.setBackground(Color.gray);
		setBounds(80, 30, 210, 210);
		pNorth.add(save);
		pNorth.add(open);
		pNorth.add(drawShape);
		pNorth.add(prScreen);
		pSouth.add(colorPanel);
		pSouth.add(eraser);
		pSouth.add(clear);
		pSouth.add(pan);
		window.add(pCenter,BorderLayout.CENTER);
		window.add(pNorth,BorderLayout.NORTH);
		window.add(pSouth,BorderLayout.SOUTH);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 80, 390, 380);
		window.setLocation(300, 300);
		window.validate();//确保窗口中的组件可以显示出来
		
	}
	
	public void paint(Graphics g){
		if(x!=-1&&y!=-1&&eraserNotice==0&&clearNotice==0){
			g.setColor(panColor);
			g2.setColor(g.getColor());
			n=v.size();
			for (int i = 0; i < n-1; i++) {
				Point p1=(Point)v.get(i);
				Point p2=(Point)v.get(i+1);
				g.drawLine(p1.getX(), p1.getY(), p2.getX(),p2.getY());
				g2.drawLine(p1.getX(), p1.getY(), p2.getX(),p2.getY());
			}
		}
		else if(eraserNotice==1&&clearNotice==0){
			g.setColor(getBackground());
			g.fillRect(x-2, y-2, 4, 4);
			g2.setColor(getBackground());
			g2.fillRect(x-2, y-2, 4, 4);
		}
		else if(clearNotice==1&&eraserNotice==0){
			g.setColor(getBackground());
			g.fillRect(0, 0, 200, 200);
			g2.setColor(getBackground());
			g2.fillRect(0, 0, 200, 200);
		}
		g.drawImage(image, 0, 0, 200, 200, this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==eraser){
			eraserNotice=1;
			clearNotice=0;
			repaint();
		}
		if(e.getSource()==clear){
			clearNotice=1;
			eraserNotice=0;
			repaint();
		}
		if(e.getSource()==pan){
			clearNotice=0;
			eraserNotice=0;
			repaint();
		}
		if(e.getSource()==open){
			FileDialog openDialog=new FileDialog(window,"open jpeg",FileDialog.LOAD);
			openDialog.setVisible(true);
			String fileName=openDialog.getDirectory()+openDialog.getFile();
			
			try {
				FileInputStream fis=new FileInputStream(fileName);
				image=ImageIO.read(fis);
				repaint();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		if(e.getSource()==save){
			FileDialog saveDialog=new FileDialog(window,"save jpeg pic",FileDialog.SAVE);
			saveDialog.setVisible(true);
			if(saveDialog.getFile()!=null){
				String fileName=saveDialog.getDirectory()+saveDialog.getFile();
				try {
					FileOutputStream fos=new FileOutputStream(fileName);
					JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(fos);
					JPEGEncodeParam param=encoder.getDefaultJPEGEncodeParam(image);
					param.setQuality(1.0f, false);
					encoder.setJPEGEncodeParam(param);
					encoder.encode(image);
					fos.close();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (ImageFormatException e2) {
					e2.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		}
		if(e.getSource()==prScreen){
			Robot robot=null;
			Rectangle rec=null;
			try {
				robot=new Robot();
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			rec=new Rectangle(300,300,(int)getToolkit().getScreenSize().getWidth(),(int)getToolkit().getScreenSize().getHeight());
			window.setVisible(false);
			image=robot.createScreenCapture(rec);
			window.setVisible(true);
			repaint();
		}
		if(e.getSource()==colorPanel){
			Color templeColor=JColorChooser.showDialog(window,"color panel",panColor);
			if(templeColor!=null){
				panColor=templeColor;
				pan.setForeground(panColor);
			}
		}
		if(e.getSource()==drawShape){
			window.dispose();
			Make canvas=new Make();
		}
		
	}

	public void mouseDragged(MouseEvent e) {
		x=e.getX();
		y=e.getY();
		Point p=new Point(x,y);
		v.add(p);
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		v.removeAllElements();
	}
	
}
