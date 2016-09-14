package com.eric.baiscKnow;
import java.util.Arrays;

public class ArrayTe {
	public static void main(String[] args) {
		int[] a=new int[5];
		int[] b=new int[5];
		for (int i = 0; i < a.length; i++) {
			a[i]=i;
		}
		System.arraycopy(a, 0, b, 0, 5);
		for (int i = 0; i < b.length; i++) {
			//System.out.println(b[i]);
		}
		for (int i = 0; i < a.length; i++) {
			;
			a[i]=(int)(Math.random()*5);
			System.out.println(a[i]);
		}
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		for (int i = 0; i < b.length; i++) {
			//System.out.println(b[i]);
		}
		
		
	}
}
