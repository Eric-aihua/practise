package com.eric.lession.hannoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class HannoiTower extends JPanel implements MouseListener,
		MouseMotionListener {
	private TowerPoint[] points;
	private Disk[] disks;
	private int diskNum;
	private int strarTowerPoint;
	private int startX, startY;
	private char[] towerNames;
	private boolean move;
	private int width, heigh;
	private JTextArea textArea;

	public HannoiTower(int diskNum, int towerWidth, int towerHeigh,
			char[] towerNames, JTextArea textArea) {
		move = false;
		towerNames = new char[] { 'a', 'b', 'c' };
		this.diskNum = diskNum;
		this.towerNames = towerNames;
		width = towerWidth;
		heigh = towerHeigh;
		this.textArea = textArea;
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
		disks = new Disk[diskNum];
		points = new TowerPoint[3 * diskNum];
		int space = 20;
		for (int i = 0; i < diskNum; i++) {/* a tower point location */
			points[i] = new TowerPoint(40 + width, 100 + space, false);
			space = space + heigh;
		}
		space = 20;
		for (int i = diskNum; i < 2 * diskNum; i++) {/*
														 * b tower point
														 * location
														 */
			points[i] = new TowerPoint(160 + width, 100 + space, false);
			space = space + heigh;
		}
		space = 20;
		for (int i = 2 * diskNum; i < 3 * diskNum; i++) {/*
															 * c tower point
															 * location
															 */
			points[i] = new TowerPoint(280 + width, 100 + space, false);
			space = space + heigh;
		}

		int tempWidth = width;
		int sub = (int) (tempWidth * 0.2);
		for (int i = diskNum - 1; i >= 0; i--) {
			disks[i] = new Disk(i, this);
			disks[i].setSize(tempWidth, heigh);
			tempWidth = tempWidth - sub;/* control disk width */
		}
		for (int i = 0; i < disks.length; i++) {
			points[i].putDisk(disks[i], this);
			if (i >= 1) {
				disks[i].setPressed(true);/*
											 * put the disk on a tower,order by
											 * disk's width
											 */
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.printComponents(g);
		g.setColor(Color.red);
		g.drawLine(points[0].getX(), points[0].getY(), points[diskNum - 1]
				.getX(), points[diskNum - 1].getY() + heigh);/* a line */
		g.drawLine(points[diskNum].getX(), points[diskNum].getY(),
				points[diskNum * 2 - 1].getX(), points[diskNum * 2 - 1].getY()
						+ heigh);/* b line */
		g.drawLine(points[diskNum * 2].getX(), points[diskNum * 2].getY(),
				points[diskNum * 3 - 1].getX(), points[diskNum * 3 - 1].getY()
						+ heigh);/* c line */
		g.drawLine(points[diskNum - 1].getX(), points[diskNum - 1].getY()
				+ heigh, points[diskNum * 3 - 1].getX(),
				points[diskNum * 3 - 1].getY() + heigh);/* botton line */
		int size = 4;
		for (int i = 0; i < points.length; i++) {/* point location */
			g.fillOval(points[i].getX() - 2, points[i].getY(), size, size);
		}
		for (int i = 0; i < 3; i++) {
			g.drawString(towerNames[i] + "塔", points[diskNum * i].getX(),
					points[diskNum * i].getY() + 120);
		}
		g.drawString("from  tower " + towerNames[0] + " to " + towerNames[1]
				+ " or tower " + towerNames[2], points[1].getX(), 400);

	}

	public void autoMove(int diskNum, char one, char two, char three) {
		if(diskNum==1){
			textArea.append(" "+one+" to "+three+"tower");
			Disk disk=getTopDisk(one);
			
		}
	}

	public Disk getTopDisk(char one) {/*获取最上面的盘子*/
		Disk disk=null;
		for (int i = 0; i < towerNames.length; i++) {
			if(towerNames[i]==one){
				for (int j = diskNum*((int)one-97); j < diskNum*((int)one-97)+diskNum; j++) {/*在对应的塔上进行遍历，找出最长面的盘子*/
					if(points[j].isHavaDisk()){
						disk= points[j].getDisk();
					}
					break;
				}
			}
		}
		return disk;
	}
	public int getTopDiskLocation(char one){/*获取最上面盘子的位置*/
		int posion=0;
		for (int i = 0; i < towerNames.length; i++) {
			if(towerNames[i]==one){
				for (int j = diskNum*((int)one-97); j < diskNum*((int)one-97)+diskNum; j++) {/*在对应的塔上进行遍历，找出最长面的盘子*/
					if(points[j].isHavaDisk()){
						posion=j;
					}
					break;
				}
			}
		}
		return posion;
	}
	public int getTopDiskFrontLocation(char one){/*获取最上面盘子的位置*/
		int posion=0;
		for (int i = 0; i < towerNames.length; i++) {
			if(towerNames[i]==one){
				for (int j = diskNum*((int)one-97); j < diskNum*((int)one-97)+diskNum; j++) {/*在对应的塔上进行遍历，找出最长面的盘子*/
					if(points[j].isHavaDisk()){
						posion=Math.max(j-1, 0);
					}
					break;
				}
			}
		}
		return posion;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		Disk disk = null;
		Rectangle rect = null;
		if (e.getSource() == this) {
			move = false;
		}
		if (e.getSource() instanceof Disk) {
			disk = (Disk) e.getSource();
			startX = (int) disk.getBounds().getX();
			startY = (int) disk.getBounds().getY();
			rect = disk.getBounds();/* get startX startY and strarTowerPoint */
			for (int i = 0; i < 3 * diskNum; i++) {
				if (rect.contains(points[i].getX(), points[i].getY())) {
					strarTowerPoint = i;
					break;
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		Disk disk = null;
		move = false;
		Rectangle rect = null;
		int x = 0, y = 0;
		boolean containPoint = false;
		int px = 0, py = 0;
		int endI=0;
		if (e.getSource() instanceof Disk) {/* contain point */
			disk = (Disk) e.getSource();
			e = SwingUtilities.convertMouseEvent(disk, e, this);
			x = e.getX();
			y = e.getY();
			rect = disk.getBounds();
			for (int i = 0; i < points.length; i++) {
				px = points[i].getX();
				py = points[i].getY();
				if (rect.contains(px, py)) {
					endI = i;
					containPoint = true;
				}
			}
			if (disk != null && containPoint) {/* contain point */
				if ((px != startX) && (py != startY)) {
					System.out.println("px  py" + px + "  " + py + "  x,  y"
							+ x + "  " + y);
					if (points[endI].isHavaDisk()) {/* point hava disk */
						System.out.println("------------------startI"
								+ strarTowerPoint);
						System.out.println("------------------endI" + endI);
						System.out.println(endI
								+ "---------point hava disk----------");
						disk.setLocation(startX, startY);
					} else {
						if (endI == diskNum - 1 || endI == diskNum * 2 - 1
								|| endI == diskNum * 3 - 1) {/* to botton */
							points[endI].putDisk(disk, this);
							System.out.println("------------------startI"
									+ strarTowerPoint);
							System.out.println("------------------endI" + endI);
							System.out
									.println("********to botton put**********************");
							points[strarTowerPoint].setHavaDisk(false);
							if (strarTowerPoint != diskNum - 1
									|| strarTowerPoint != diskNum * 2 - 1
									|| strarTowerPoint != diskNum * 3 - 1) {/*
																			 * not
																			 * from
																			 * botton
																			 */
								(points[strarTowerPoint + 1].getDisk())
										.setPressed(false);
								points[strarTowerPoint].setHavaDisk(false);
							} else {
								points[strarTowerPoint].setHavaDisk(false);
							}
						} else {/* not to botton */
							if (points[endI + 1].isHavaDisk()) {/*
																 * next have
																 * disk
																 */
								Disk tempDisk = points[endI + 1].getDisk();
								if ((tempDisk.getNum() - disk.getNum()) >= 1) {/*
																				 * bigger
																				 * than
																				 * points[EndI+1]
																				 */
									points[endI].putDisk(disk, this);
									System.out
											.println("------------------startI"
													+ strarTowerPoint);
									System.out.println("------------------endI"
											+ endI);
									System.out
											.println("********not to botton and next hava disk put**********************");
									points[strarTowerPoint].setHavaDisk(false);
									System.out.println("#################"
											+ points[strarTowerPoint]
													.isHavaDisk());
									System.out.println("#################"
											+ points[endI].isHavaDisk());
									if (strarTowerPoint != diskNum - 1
											|| strarTowerPoint != diskNum * 2 - 1
											|| strarTowerPoint != diskNum * 3 - 1) {
										(points[strarTowerPoint].getDisk())
												.setPressed(false);
										points[strarTowerPoint]
												.setHavaDisk(false);
										tempDisk.setPressed(true);
									} else {
										points[strarTowerPoint]
												.setHavaDisk(false);
										// points[endI].setHavaDisk(true);
										tempDisk.setPressed(true);
									}
								} else {/* small than points[EndI+1] */
									System.out
											.println("----------small---------");
									disk.setLocation(startX, startY);
								}
							} else {/* next not have disk */
								int minMoveNum = ((endI / diskNum) + 1)
										* diskNum;
								for (int j = endI + 1; j < minMoveNum; j++) {
									if (points[j].isHavaDisk()) {

									}
								}
								System.out
										.println("---------next not have disk----------");
								disk.setLocation(startX, startY);

							}
						}
					}
				}
			}
			if (disk != null && !containPoint) {/* not contain point */
				System.out.println("---------not contain point----------");
				disk.setLocation(startX, startY);
			}

		}
	}

	public void mouseDragged(MouseEvent e) {
		Disk disk = null;
		int x, y;
		if (e.getSource() instanceof Disk) {
			move = true;
			disk = (Disk) e.getSource();
			e = SwingUtilities.convertMouseEvent(disk, e, this);
		}
		if (e.getSource() == this) {
			if (move && (disk != null)) {
				x = e.getX();
				y = e.getY();
				if (disk.isPressed() == false) {
					disk.setLocation(x - disk.getWidth() / 2, y
							- disk.getHeight() / 2);
				} else {
					System.out.println("locked");
				}
			}
		}

	}

	public void mouseMoved(MouseEvent e) {

	}

}
