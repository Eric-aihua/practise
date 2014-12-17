package com.eric.object;
//when use javadoc command, please add -encoding arguement!

/**
 * @author Eric.sun
 * @see test.java
 * @since 
 * */
public class PrintArguement {
	public static void main(String args[]){
		for(String str:args){
			System.out.println(str);
		}
	}
}
