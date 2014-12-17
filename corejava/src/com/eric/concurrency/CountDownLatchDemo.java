/**
 * 
 */
package com.eric.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric
 * 
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		/*
		 * CountDownLatch's parameter will decide whether to print waiting
		 * information if parameter >working parameter will never can't to
		 * execute if parameter==0 , don't need to waiting call working method
		 */
		
		CountDownLatch latch = new CountDownLatch(10);
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			es.execute(new WaitingTask(latch));
		}
		for (int i = 0; i < 10; i++) {
			es.execute(new WokingTask(latch));
		}
		es.shutdown();
		System.out.println("Existing");
	}
}

class WokingTask implements Runnable {
	private static int	         count	= 0;
	private int	                 id	   = count++;
	private final CountDownLatch	latch;
	
	public WokingTask(CountDownLatch latch) {
		this.latch = latch;
		
	}
	
	public String toString() {
		return "working:" + id;
	}
	
	public void run() {
		try {
			working();
			latch.countDown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void working() {
		System.out.println(this + "working");
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class WaitingTask implements Runnable {
	private static int	         count	= 0;
	private int	                 id	   = count++;
	private final CountDownLatch	latch;
	
	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public String toString() {
		return "waiting:" + id;
	}
	
	public void run() {
		try {
			latch.await();
			System.out.println(this + " waiting finished....");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
