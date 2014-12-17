package com.eric.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Sketch {
	public static void main(String[] args) {
		new SketchFrame();
		ActionListener al=new moveAction();
		Timer t=new Timer(1000, al);
		t.start();
	}

}

class SketchFrame extends JFrame {
	public SketchFrame() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new SketchJPanel());
		setVisible(true);
	}
}

class SketchJPanel extends JPanel {
	private Point2D last;
	private ArrayList<Line2D> lines;
	private static final int SMALL_INCREMENT = 1;
	private static final int LARGE_INCREMENT = 5;

	public SketchJPanel() {
		last = new Point2D.Double(100, 100);
		lines = new ArrayList<Line2D>();
		KeyHandler kh = new KeyHandler();
		
		
		addKeyListener(kh);
		setFocusable(true);
	}

	public void add(int dx, int dy) {
		Point2D end = new Point2D.Double(last.getX() + dx, last.getY() + dy);
		Line2D line = new Line2D.Double(last, end);
		lines.add(line);
		repaint();
		last = end;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (Line2D l : lines) {
			System.out.println(l.getX1() + "/x1;x2/" + l.getX2());
			System.out.println(l.getY1() + "/y1;y2/" + l.getY2());
			g2.draw(l);
		}
	}

	class KeyHandler implements KeyListener {

		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			int d;
			if (e.isShiftDown()) {
				d = LARGE_INCREMENT;
			} else {
				d = SMALL_INCREMENT;
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				add(-d, 0);
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				add(d, 0);
			}
			if (keyCode == KeyEvent.VK_UP) {
				add(0, -d);
			}
			if (keyCode == KeyEvent.VK_DOWN) {
				add(0, d);
			}

		}

		public void keyReleased(KeyEvent e) {
			System.out.println("key repleased!");
		}

		public void keyTyped(KeyEvent e) {
			char keyCode = e.getKeyChar();
			int d;
			if (Character.isUpperCase(keyCode)) {
				d = LARGE_INCREMENT;
				keyCode = Character.toLowerCase(keyCode);
			} else {
				d = SMALL_INCREMENT;
			}
			if (keyCode == 'h') {
				add(-d, 0);
			}
			if (keyCode == 'j') {
				add(d, 0);
			}
			if (keyCode == 'k') {
				add(0, -d);
			}
			if (keyCode == 'l') {
				add(0, d);
			}

		}

	}
	
}
class moveAction implements ActionListener{
	public void actionPerformed(ActionEvent e){
		System.out.println(new Date());
	}
}
