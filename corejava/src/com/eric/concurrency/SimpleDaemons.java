package com.eric.concurrency;

import java.util.concurrent.TimeUnit;

/*
 * 
 * */
public class SimpleDaemons implements Runnable {
	public void run() {
		
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println("Current Thread:" + Thread.currentThread() + " isDeamon:" + Thread.currentThread().isDaemon());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new SimpleDaemons());
			// if not set Daemon thread, this problem will running forever
			t.setDaemon(true);
			t.start();
		}
		// if haven't sleep, terminal nothing ouput
		// 如果没有睡眠时间的话，因为后台线程在前面已经有strat（）了，但是为什么没有输出了？
		// Thread.sleep(5000);
		System.out.println("Ended Main thread");
	}
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */
