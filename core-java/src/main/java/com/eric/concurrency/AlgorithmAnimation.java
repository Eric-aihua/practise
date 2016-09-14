package com.eric.concurrency;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlgorithmAnimation {
	public static void main(String[] args) {
		new AlgorithJFrame();
	}
}

class AlgorithJFrame extends JFrame {
	private AlgorithJPanel	aj;
	private JButton	        run;
	private JButton	        step;
	public static final int	ARRAY_LENGTH	= 20;
	
	public AlgorithJFrame() {
		Double[] valueArr = new Double [ARRAY_LENGTH];
		aj = new AlgorithJPanel();
		final Sorted sorted = new Sorted(valueArr, aj);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("AlgorthJFrame");
		aj = new AlgorithJPanel();
		run = new JButton("run");
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorted.setRun();
			}
		});
		step = new JButton("step");
		step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorted.setStep();
			}
		});
		Thread t = new Thread(sorted);
		aj.add(run);
		aj.add(step);
		add(aj);
		setVisible(true);
		t.start();
		
	}
	
}

class AlgorithJPanel extends JPanel {
	
	private Double	 market1;
	private Double	 market2;
	private Double[]	values;
	
	public Double[] getValues() {
		return values;
	}
	
	public void setValues(Double[] values) {
		this.values = values;
	}
	
	public void setValues(Double[] values, Double market1, Double market2) {
		this.market1 = market1;
		this.market2 = market2;
		this.values = values;
		initArray(values);
		repaint();
	}
	
	public AlgorithJPanel() {
		setSize(400, 500);
		setVisible(true);
		values = new Double [20];
		setValues(values, market1, market2);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (values == null)
			return;
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth() / values.length;
		for (int i = 0; i < values.length; i++) {
			double heigh = getHeight() * values[i];
			System.out.println("market1=" + market1 + "market2" + market2);
			Rectangle2D bar = new Rectangle2D.Double(width * i, 0, width, heigh);
			if (values[i] == market1 || values[i] == market2) {
				g2.fill(bar);
			} else {
				g2.draw(bar);
			}
		}
	}
	
	public static void initArray(Double[] values) {
		for (int i = 0; i < 20; i++) {
			values[i] = new Double(Math.random());
		}
	}
	
}

class Sorted implements Runnable {
	private Double[]	    values;
	private AlgorithJPanel	panel;
	private Semaphore	    gate;
	public static final int	DELAY	= 100;
	public boolean	        run;
	
	public Sorted(Double[] values, AlgorithJPanel panel) {
		this.values = values;
		this.panel = panel;
		gate = new Semaphore(1);
		run = false;
	}
	
	public void setRun() {
		run = true;
		gate.release();
	}
	
	public void setStep() {
		run = false;
		gate.release();
	}
	
	public void run() {
		Comparator<Double> cmp = new Comparator<Double>() {
			public int compare(Double market1, Double market2) {
				panel.setValues(values, market1, market2);
				System.out.println("market1=" + market1 + "market2" + market2);
				System.out.println("panelArray 1:" + panel.getValues()[0] + "panelArray 2:" + panel.getValues()[1]);
				try {
					if (run) {
						Thread.sleep(1000);
					} else {
						gate.acquire();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
				return market1.compareTo(market2);
			}
		};
		Arrays.sort(values, cmp);
		panel.setValues(values, null, null);
	}
}
