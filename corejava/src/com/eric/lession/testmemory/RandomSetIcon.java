package com.eric.lession.testmemory;

import java.util.Vector;

import javax.swing.ImageIcon;

public class RandomSetIcon {
	public RandomSetIcon(){}
	
	public void setRandomIcon(Block[] block,ImageIcon[] icon){
		Vector vector=new Vector();
		int n=icon.length;
		for (int i = 0; i < block.length; i++) {
			int x=i%n;
			Point p=new Point(x);
			vector.add(p);
		}
		for (int i = 0; i < block.length; i++) {
			int m=(int)(Math.random()*vector.size());
			Point p=(Point)vector.elementAt(m);
			int x=p.getX();
			block[i].setRollIcon(icon[m%n]);
			vector.remove(m);
		}
	}

}
class Point{
	private int x;

	public Point(int x) {
		super();
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
}
