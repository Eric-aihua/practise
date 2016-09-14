package com.eric.concurrency;

import java.util.Date;

public class SleepTest {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(new Date().getSeconds());
		Thread.sleep(10000);
		System.out.println(new Date().getSeconds());
		
	}
}
