package com.eric.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 这个程序主要掩饰了在程序中队Runnable异常的捕获方法
 * 
 * @author Eric
 * 
 */
public class ExceptionThread {
	
	public static void main(String[] args) {
		try {
			CatchException.catchExceptionByUncaughtExceptionHandler();
			System.out.println("************************************");
			// CatchException.catchExceptionByCatch();
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}
}

class ExceptionRunnable implements Runnable {
	public void run() {
		throw new RuntimeException("Throw By Runnable");
	}
	
}

// 用普通的catch方法捕获
class CatchException {
	public static void catchExceptionByCatch() {
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new ExceptionRunnable());
		
	}
	
	/*
	 * use try{} catch{} block can't catch exception from Runnable,to solve the
	 * problem. Thread.UncaughtExceptionHandler is a new interface in Java SE5;
	 * it allows you o attach an exception handler to each Thread object.
	 * Thread.UncaughtExceptionHandler.uncaughtException( ) is automatically
	 * called when that thread is about to die from an uncaught exception.
	 */
	public static void catchExceptionByUncaughtExceptionHandler() {
		Thread t = new Thread(new ExceptionRunnable());
		t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Exception:" + e.getMessage() + " was catched");
			}
		});
		t.start();
	}
}
