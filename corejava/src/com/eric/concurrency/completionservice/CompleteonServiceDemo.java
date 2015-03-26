package com.eric.concurrency.completionservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompleteonServiceDemo {

	public static void main(String args[]) {
		//runByFutureMap();
		//runByCompletionService(new SimpleRunnable());
		runByCompletionService(new SimpleCallable());
	}

	/**
	 * 通过使用CompletionService来保证多个多个需要同时运行的进程的运行
	 */
	public static void runByCompletionService(Runnable thread) {
		try {
			final ExecutorService executor = Executors.newFixedThreadPool(10);
			CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
			for (int i = 0; i < 10; i++) {
				completionService.submit(thread,10);
			}
			System.out.print("###########################");
			for (int i = 0; i < 10; i++) {
				Future<Integer> future;
				future = completionService.take();
				System.out.print("线程" + i + "任务处理完成：" + future.get() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 通过使用CompletionService来保持多个同时运行的callable的返回结果
	 */
	public static void runByCompletionService(Callable<Integer> callable) {
		try {
			final ExecutorService executor = Executors.newFixedThreadPool(10);
			CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
			for (int i = 0; i < 10; i++) {
				completionService.submit(callable);
			}
			System.out.print("###########################");
			for (int i = 0; i < 10; i++) {
				Future<Integer> future;
				future = completionService.take();
				System.out.print("线程" + i + "任务处理完成：" + future.get() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 通过将Future放到Map中的方式来实现多个SampleRunnable的同时执行
	 */
	public static void runByFutureMap() {
		final ExecutorService executor = Executors.newFixedThreadPool(10);
		ConcurrentHashMap<Integer, Future> futureMap = new ConcurrentHashMap();
		try {
			for (int i = 0; i < 10; i++) {
				Future future = executor.submit(new SimpleRunnable());
				futureMap.putIfAbsent(i, future);
			}
			System.out.print("###########################");
			for (Integer index : futureMap.keySet()) {
				Future future = futureMap.get(index);
				System.out.print("线程" + index + "任务处理完成：" + future.get() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
