package com.eric.lession.hannoi;


public class TowerPoint {
	private int x,y;
	private boolean havaDisk;
	private Disk disk;
	private HannoiTower ht;
	public TowerPoint(int x, int y, boolean havaDisk) {
		super();
		this.x = x;
		this.y = y;
		this.havaDisk = havaDisk;
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
	public boolean isHavaDisk() {
		return havaDisk;
	}
	public void setHavaDisk(boolean havaDisk) {
		this.havaDisk = havaDisk;
	}
	public Disk getDisk() {
		return disk;
	}
	public void setDisk(Disk disk) {
		this.disk = disk;
	}
	public HannoiTower getHt() {
		return ht;
	}
	public void setHt(HannoiTower ht) {
		this.ht = ht;
	}
	public  void putDisk(Disk disk,HannoiTower ht){
		int w=disk.getBounds().width;
		int h=disk.getBounds().height;
		this.ht=ht;
		this.disk=disk;
		disk.setBounds(x-w/2, y-h/w, w, h);
		ht.setLayout(null);
		ht.add(disk);
		havaDisk=true;
	}
	
}
