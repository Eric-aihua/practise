package com.eric.concurrency;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 这个程序主要掩饰了在程序中队Runnable异常的捕获方法
 * 
 * @author Eric
 */
public class ExceptionThread {

	public static void main(String[] args) {
		try {
			// CatchException.catchExceptionByUncaughtExceptionHandler();
			System.out.println("************************************");
			CatchException.catchSubmitException();
		} catch (Exception ex) {
			ex.printStackTrace();
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

	public static void catchSubmitException() {
		try {
			ExecutorService es = Executors.newCachedThreadPool();
			Future<Integer> future = es.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(2000);
					//throw new RuntimeException("处理数据期间产生异常");
					return 10;
				}
			});
			System.out.println("处理其他任务1");
			System.out.println(future.get());
			Thread.sleep(500);
			System.out.println("处理其他任务2");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

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
