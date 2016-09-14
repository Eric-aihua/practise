package com.eric.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 主要测试通过同步机制来控制偶数的生成
 * 
 * @author Eric
 * 
 */
public class EvenBySynTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 10000; i++) {
			// es.execute(new EvenNotSyn());
			// es.execute(new EvenBySynClass());
			es.execute(new EvenByLockClass());
		}
		System.out.println("Not Even num is" + EvenNotSyn.maps.entrySet().size());
		es.shutdown();
	}
}

// 使用Lock 作为同步机制,得到的结果都是偶数的
/*
 * Although the try-finally requires more code than using the synchronized
 * keyword, it also represents one of the advantages of explicit Lock objects.
 * If something fails using the synchronized keyword, an exception is thrown,
 * but you don’t get the chance to do any cleanup in order to maintain your
 * system in a good state. With explicit Lock objects, you can maintain proper
 * state in your system using the finally clause.
 */
class EvenByLockClass implements Runnable {
	static int	                num	 = 0;
	static Map<String, Integer>	maps	= new HashMap<String, Integer>();
	
	public void run() {
		
		even();
		if (num % 2 != 0) {
			maps.put(Thread.currentThread() + "@", num);
			// System.out.println("Thread" + Thread.currentThread().getId() +
			// " num:" + num + "not enen");
		}
	}
	
	// 锁控制同步的方法
	public synchronized void even() {
		Lock lock = new ReentrantLock();
		try {
			
			lock.lock();
			
			num++;
			num++;
		} finally {
			
			lock.unlock();
		}
		
	}
}

// 使用synchronized 作为同步机制,得到的结果都是偶数的
class EvenBySynClass implements Runnable {
	static int	                num	 = 0;
	static Map<String, Integer>	maps	= new HashMap<String, Integer>();
	
	public void run() {
		
		even();
		if (num % 2 != 0) {
			maps.put(Thread.currentThread() + "@", num);
			// System.out.println("Thread" + Thread.currentThread().getId() +
			// " num:" + num + "not enen");
		}
	}
	
	// 同步的方法
	public synchronized void even() {
		num++;
		num++;
		
	}
}

// 非同步方法的runnable,得到的结果包含非偶数
class EvenNotSyn implements Runnable {
	static int	                num	 = 0;
	static Map<String, Integer>	maps	= new HashMap<String, Integer>();
	
	public void run() {
		
		even();
		if (num % 2 != 0) {
			maps.put(Thread.currentThread() + "@", num);
			// System.out.println("Thread" + Thread.currentThread().getId() +
			// " num:" + num + "not enen");
		}
	}
	
	public void even() {
		num++;
		num++;
		
	}
}
