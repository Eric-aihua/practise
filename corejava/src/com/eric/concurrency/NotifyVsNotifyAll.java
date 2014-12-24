/**
 * 本程主要用来证明notify与notityAll()都是用来唤醒等待特定条件的block,notifyAll()并不是唤醒所有的等待线程
 */
package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric
 * 
 */
public class NotifyVsNotifyAll {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			es.execute(new Task1());
		}
		for (int i = 0; i < 5; i++) {
			es.execute(new Task2());
		}
		// 如果调用Tas1.block.unlockOne(),则只有其中的一个Task1的线程被唤醒
		TimeUnit.SECONDS.sleep(1);
		System.out.println("just call Task1.block.unlockOne()");
		Task1.block.unlockOne();
		// 如果调用Tas1.block.unlockAll(),则所有Task1的线程被唤醒,而Task2的线程不会被唤醒
		TimeUnit.SECONDS.sleep(1);
		System.out.println("just call Task1.block.unlockAll()");
		Task1.block.unlockAll();
		// 如果调用Tas2.block.unlockAll(),则所有Task2的线程被唤醒,而Task1的线程不会被唤醒
		TimeUnit.SECONDS.sleep(1);
		System.out.println("just call Task2.block.unlockAll()");
		Task2.block.unlockAll();
		es.shutdownNow();
	}
}

// 定义两个有相同实现的task
class Task1 implements Runnable {
	static Block	block	= new Block();
	
	public void run() {
		block.lock();
	}
}

class Task2 implements Runnable {
	static Block	block	= new Block();
	
	public void run() {
		block.lock();
	}
}

class Block {
	// 只要不被中断就一直处于等待状态
	synchronized void lock() {
		try {
			while (!Thread.interrupted()) {
				wait();
				System.out.println(Thread.currentThread() + " was wakeup");
			}
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread()+"被main 中断且退出");
		}
	}
	
	synchronized void unlockOne() {
		notify();
	}
	
	synchronized void unlockAll() {
		notifyAll();
	}
}
