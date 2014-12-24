package com.eric.oopbasic;

public class ChangNumberParam {
	public static void main(String[] args) {
		param(5,6,4,5);
		System.out.println(maxNum(1,7,8,54,2,9,4,6,5,7,5,1,24,5));
	}
	public static void param(int... a){
		for (int i : a) {
			System.out.println(a);
		}
	}
	public static int maxNum(int...number){
		int d=Integer.MIN_VALUE;
		for (int i : number) {
			if(i>d){
				d=i;
			}
		}
		return d;
	}
}
