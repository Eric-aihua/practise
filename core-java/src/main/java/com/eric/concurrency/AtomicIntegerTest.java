package com.eric.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用atomic 类来验证属性在多个线程中的可视性问题,如果用注释部分的代码执行递增的话,可能会存在同步的问题,
 * 该程序中用AtomicInteger来保证递增2的同步操作
 * 
 * @author Eric
 * 
 */
public class AtomicIntegerTest implements Runnable {
	private AtomicInteger	ai	= new AtomicInteger();
	
	// int ai;
	
	public void increment() {
		ai.addAndGet(2);
		// ai++;
		// ai++;
	}
	
	public void run() {
		while (true) {
			increment();
		}
	}
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.out.println("exit...");
				System.exit(0);
			}
		}, 5000);
		ExecutorService es = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		es.execute(ait);
		// while(ait.ai.get()%2!=0){
		while (true) {
			int value = ait.ai.get();
			if (value % 2 != 0) {
				System.out.println("even:" + value);
				System.exit(0);
			}
			
		}
	}
}
