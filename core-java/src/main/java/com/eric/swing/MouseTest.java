package com.eric.swing;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseTest {
	public static void main(String[] args) {
		new MouseJFrame();
	}
}

class MouseJFrame extends JFrame {
	public MouseJFrame() {
		add(new MouseJPanel());
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	};
}

class MouseJPanel extends JPanel {
	private ArrayList<Rectangle2D> rectangles;
	private static final int SIDELENGTH = 20;
	private Rectangle2D current;

	public MouseJPanel() {
		setSize(500, 500);
		rectangles = new ArrayList<Rectangle2D>();
		MouseHandler mh = new MouseHandler();
		MouseMotionHandler mmh=new MouseMotionHandler();
		addMouseListener(mh);
		addMouseMotionListener(mmh);

	}

	public Rectangle2D find(Point2D p) {
		for (Rectangle2D rec : rectangles) {
			if (rec.contains(p)) {
				return rec;
			}
		}
		return null;
	}

	public void add(Point2D p) {
		double x = p.getX();
		double y = p.getY();
		current = new Rectangle2D.Double(x - SIDELENGTH / 2,
				y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		rectangles.add(current);
		repaint();
	}

	public void remove(Rectangle2D re) {
		if (re == null)
			return;
		if (re == current) {
			current = null;
			rectangles.remove(re);
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Rectangle2D rec : rectangles) {
			g2.draw(rec);
		}
	}

	class MouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			int count = e.getClickCount();
			current = find(e.getPoint());
			if (current != null & count == 2) {
				remove(current);
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			current = find(e.getPoint());
			if (current == null) {
				add(e.getPoint());
			}
		}

	}
	class MouseMotionHandler extends MouseMotionAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			current=find(e.getPoint());
			if(current==null){
				setCursor(Cursor.getDefaultCursor());
			}
			else{
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
					
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);
			current=find(e.getPoint());
			if(current!=null){
				int x=e.getX();
				int y=e.getY();
				current.setFrame(x - SIDELENGTH / 2,
				y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
		rectangles.add(current);
		repaint();
			}
		}
		
	}
}
