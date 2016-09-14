package com.eric.baiscKnow;

public class EveryYearThoundsFromBank {
	public static void main(String[] args) {
		double tatal=0;
		for(int i=1;i<=5;i++){
			tatal=(tatal+1000)/(1+0.0063*12);
		}
		System.out.println(tatal);
	}
}
