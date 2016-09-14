package com.eric.baiscKnow;

import java.math.BigInteger;
import java.util.Scanner;

public class BidIntegerTest {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("input total number:");
		int all=sc.nextInt();
		System.out.println("input times:");
		int times=sc.nextInt();
		BigInteger allT=BigInteger.valueOf(1);
		
		for (int i = 0; i < times; i++) {
			allT=allT.multiply(BigInteger.valueOf(all-i+1));
		}
		System.out.println(allT.toString());
		System.out.println(Integer.MAX_VALUE);
	}

}
