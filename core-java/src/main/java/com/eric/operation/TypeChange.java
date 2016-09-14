package com.eric.operation;
/**
 * boolean can't to change any type, or else
 * */
public class TypeChange {
	public static void main(String[] args) {
	    boolean b=false;
	    //int a=(int)b;
	    int a1=5;
	    //b=(boolean)(a1);
	    
	    float f1=0.7f;
	    float f2=0.4f;
	    System.out.println("f1:"+f1+" (int)f1:"+(int)f1);
	    System.out.println("f2:"+f2+" (int)f2:"+(int)f2);
	    
	    int i3=10;
	    float f3=(float)i3;
	    System.out.println("f3:"+f3);
	    
    }
}
