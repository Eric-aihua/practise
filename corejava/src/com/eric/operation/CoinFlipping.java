package com.eric.operation;

import java.util.Random;

public class CoinFlipping {
	public static void main(String args[]){
		Random random=new Random();
		int ranInt=random.nextInt(10);
		System.out.println(ranInt);
		if(ranInt%2==0){
			System.out.println("the obverse side!");
		}
		else{
			System.out.println("reverse side!");
		}
	}
}
