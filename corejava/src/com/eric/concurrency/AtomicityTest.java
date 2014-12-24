package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主要验证在java中++操作为非原子性的操作
 * 
 * @author Eric
 * 
 */
public class AtomicityTest implements Runnable {
	private int	value;
	
	/*
	 * 如果getValue() 不是同步的,则会出现非原子性的问题
	 */
	public  int getValue() {
		return value;
	}
	
	public synchronized void increment() {
		value++;
		value++;
	}
	
	public void run() {
		while (true) {
			increment();
		}
	}
	
	public static void main(String args[]) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		AtomicityTest at = new AtomicityTest();
		es.execute(at);
		while (true) {
			int value = at.getValue();
			if (value % 2 != 0) {
				System.out.println("not even:" + value);
				System.exit(0);
			}
		}
	}
	
}
