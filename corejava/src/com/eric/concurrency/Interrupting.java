package com.eric.concurrency;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 本类主要展示了对线程的中断方法得到的结论为 1:休眠的阻塞可以被中断 2:IO以及同步的阻塞不会被中断
 * 
 * @author Eric,
 * 
 */
public class Interrupting {
	public static void main(String[] args) throws InterruptedException {
		interrupt(new SleepBlock());// 会被中断
		// interrupt(new IOBlock(System.in));// 不会被中断,会一直等待用户的输入
		interrupt(new SynBlock());// 也不会被中断
	}
	
	static void interrupt(Runnable runnable) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		System.out.println("开始执行:" + runnable.getClass().getSimpleName());
		Future<?> future = es.submit(runnable);
		// 如果要是不加这一行,则当执行IOBlock时,还没有执行到is.read()方法就已经被中断了,那么中断的效果就看不出来了,通过这里加上一秒的休眠时间,使is.read()可以被执行,且中断不会起作用
		TimeUnit.SECONDS.sleep(1);
		future.cancel(true);
		
		es.shutdownNow();
		// System.out.println(runnable.getClass().getSimpleName() + "被中断");
	}
}

// 该线程执行的时候将会休眠1000秒
class SleepBlock implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1000);
		} catch (Exception ex) {
			System.out.println("Sleep Block 被中断.");
		}
		System.out.println("SleepBlock 执行完毕");
	}
}

// IO阻塞不会被中断
class IOBlock implements Runnable {
	public InputStream	is;
	
	public IOBlock(InputStream is) {
		this.is = is;
	}
	
	public void run() {
		try {
			System.out.println("请输入:");
			is.read();
		} catch (Exception ex) {
			System.out.println("IO Block 被中断");
		}
	}
}

// 本类的效果为,当在run()方法中调用f()时,f()由于是死循环,导致run()方法的执行被阻塞
class SynBlock implements Runnable {
	public void run() {
		try {
			System.out.println("开始调用f()");
			f();
			System.out.println("f()调用完毕");
		} catch (Exception ex) {
			System.out.println("SynBlock 被中断");
		}
		
	}
	
	public synchronized void f() {
		while (true) {}
	}
}
