package com.eric.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawPan {
	public static void main(String[] args) {
		new DrawJFrame();
	}

}

class DrawJFrame extends JFrame {
	public DrawJFrame() {
		setSize(500, 500);
		setVisible(true);
		DrawPanel dp = new DrawPanel();
		add(dp);
	}
}

class DrawPanel extends JPanel {
	private Line2D line;
	private List<Line2D> lines;
	private Point2D p;
	private Point2D p1;

	public DrawPanel() {
		setSize(500, 500);
		MouseHander mh = new MouseHander();
		MouseDeagedHandel mmh = new MouseDeagedHandel();
		line=new Line2D.Double();
		lines=new ArrayList<Line2D>();
		addMouseMotionListener(mmh);
		addMouseListener(mh);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Line2D l : lines) {
			g2.draw(line);
			
		}
	}

	class MouseHander extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			p = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			p1 = e.getPoint();
			line.setLine(p, p1);
			lines.add(line);
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			p1 = e.getPoint();
			line.setLine(p, p1);
			lines.add(line);
			repaint();
		}
		
		
		

	}

	class MouseDeagedHandel implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {
			p1 = e.getPoint();
			line.setLine(p, p1);
			lines.add(line);
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			
			//lines.add(new Line2D.Double(,e.getPoint()));
			repaint();
			
		}

	}
}
