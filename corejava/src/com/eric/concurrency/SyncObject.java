package com.eric.concurrency;

/**
 * 这个程序用来证明对象锁的作用
 * 在g()方法中,如果去掉对obj的同步的话,则表示f()和g()公用DualSynch的this的对象锁,有因为f()为synchronized
 * ,所以结果将是连续输出10个f(),然后再输出是个g()
 * 
 * @author Eric
 * 
 */
public class SyncObject {
	static DualSynch	ds	= new DualSynch();
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				ds.f();
			}
		}).start();
		// run g() method in main thread
		ds.g();
	}
}

class DualSynch {
	Object	obj	= new Object();
	
	public synchronized void f() {
		for (int i = 0; i < 10; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}
	
	public void g() {
		// locked on obj
		synchronized (obj) {
			for (int i = 0; i < 10; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
	
}
