package com.eric.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 在一个后台线程里创建的所有线程都为后台线程,如果设置为后台线程,则当main结束时线程执行将终止
 * 后台线程的例子可以参见SimpleDaemons.java
 * 
 * @author Eric
 * 
 */
public class Daemons {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Daemon("Daemon1"));
		t1.setDaemon(true);
		t1.start();
		try {
			TimeUnit.MILLISECONDS.sleep(800);
			// 如果t2不设置为后台线程,则该程序会一直执行
			// Thread t2 = new Thread(new Daemon("Daemon2"));
			// t2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

// 第一层线程
class Daemon implements Runnable {
	String	name;
	
	public Daemon(String name) {
		this.name = name;
		
	}
	
	List<Thread>	threads	= new ArrayList<Thread>();
	Thread	     thread;
	
	public void run() {
		System.out.println("Daemon:" + name + Thread.currentThread().isDaemon());
		for (int i = 0; i < 10; i++) {
			thread = new Thread(new DaemonSpawn());
			System.out.println(name + ".DaemonSpawn" + i + ":" + Thread.currentThread() + " is Daemon:" + thread.isDaemon());
			thread.start();
		}
	}
}

// 被线程创建的线程(第二层线程)
class DaemonSpawn implements Runnable {
	public void run() {
		while (true) {
			try {
				System.out.println("Current Thread:" + Thread.currentThread());
				// TimeUnit.MILLISECONDS.sleep(1000);
				Thread.yield();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
