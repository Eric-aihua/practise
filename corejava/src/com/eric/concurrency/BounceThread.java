package com.eric.concurrency;

/**
 * 本程序主要演示通过多线程来控制多个小球产生碰撞的效果
 * */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*当没有执行完start后，exit执行无效*/
class BounceThread {
	public static void main(String[] args) {
		new BallJFrame();
	}
}

class Ball {
	private static final double	width	= 15;
	private static final double	heigh	= 15;
	private double	            x	  = Math.random() * 200;
	private double	            y	  = Math.random() * 200;
	private double	            dx	  = 1;
	private double	            dy	  = 1;
	
	public Ellipse2D getEllipse() {
		return new Ellipse2D.Double(x, y, width, heigh);
	}
	
	public void move(Rectangle2D rec) {
		x += dx;
		y += dy;
		if (x < rec.getMinX()) {
			x = rec.getMinX();
			dx = -dx;
		}
		if (x + width >= rec.getMaxX()) {
			x = rec.getMaxX() - width;
			dx = -dx;
		}
		if (y < rec.getMinY()) {
			y = rec.getMinY();
			dy = -dy;
		}
		if (y + heigh >= rec.getMaxY()) {
			y = rec.getMaxY() - heigh;
			dy = -dy;
		}
	}
}

class BallPanel extends JPanel {
	
	private int	width	= 450;
	private int	heigh	= 450;
	
	public BallPanel() {
		add(addButton("start", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBall();
			}
			
		}));
		add(addButton("close", new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) {
			g2.fill(b.getEllipse());
		}
	}
	
	private ArrayList<Ball>	balls	= new ArrayList<Ball>();
	
	public void add(Ball b) {
		balls.add(b);
	}
	
	public JButton addButton(String tile, ActionListener listener) {
		JButton button = new JButton(tile);
		button.addActionListener(listener);
		return button;
		
	}
	
	public void addBall() {
		Ball ball = new Ball();
		add(ball);
		Thread t = new Thread(new BallRunable(ball, this));
		t.start();
	}
}

class BallRunable implements Runnable {
	private Ball	  ball;
	private Component	component;
	private int	      setps	= 100000;	// 用于控制执行的时间
	private int	      delay	= 2;	  // 用于移动的频率
	                                  
	public BallRunable(Ball ball, Component component) {
		super();
		this.ball = ball;
		this.component = component;
	}
	
	public void run() {
		for (int i = 1; i < setps; i++) {
			ball.move(component.getBounds());
			component.repaint();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class BallJFrame extends JFrame {
	public BallJFrame() {
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new BallPanel());
		setVisible(true);
	}
}
