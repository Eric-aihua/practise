package com.eric.jvm.executor;

import java.io.Serializable;

/**
 * 静态分派中,方法优先级的例子
 * @author Eric
 *
 */
public class MethodOverloadDemonstrate {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MethodOverloadDemonstrate demonstrate=new MethodOverloadDemonstrate();
		demonstrate.overLoad('c');
	}
	
	public void overLoad(char c){System.out.println("char");}
	public void overLoad(int c){System.out.println("int");}
	public void overLoad(long c){System.out.println("long");}
	public void overLoad(float c){System.out.println("float");}
	public void overLoad(double c){System.out.println("double");}
	public void overLoad(Character c){System.out.println("Character");}
	public void overLoad(Serializable c){System.out.println("Serializable");}
	public void overLoad(Object... c){System.out.println("Object...");}
	public void overLoad(Object c){System.out.println("Object");}
	public void overLoad(Integer c){System.out.println("Integer");}
	public void overLoad(Comparable<Character> c){System.out.println("Comparable<Character>");}
	
}
