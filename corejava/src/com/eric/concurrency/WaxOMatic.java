/**
 * 本类主要通过一个汽车打蜡的例子来展示wait()以及notifyAll()的配合使用
 * 具体的规则是只有打完蜡之后才可以进行抛光
 * 
 * 
 */
package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric
 * 
 */
public class WaxOMatic {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		try {
			Car car = new Car("BMW");
			es.execute(new WaxOnTask(car));
			es.execute(new WaxOffTask(car));
			TimeUnit.MILLISECONDS.sleep(3000);
			es.shutdownNow();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}
	
}

// 对车进行打蜡的任务
class WaxOnTask implements Runnable {
	private Car	car;
	
	public WaxOnTask(Car car) {
		this.car = car;
	}
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				// 对车进行打蜡
				car.waxOn();
				TimeUnit.MILLISECONDS.sleep(200);
				// 等待抛光
				car.waitingBuffered();
			} catch (InterruptedException e) {
				System.out.println("打蜡被中断");
			}
		}
		System.out.println("打蜡结束");
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
}

// 对车进行抛光的任务
class WaxOffTask implements Runnable {
	private Car	car;
	
	public WaxOffTask(Car car) {
		this.car = car;
	}
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				// 对车进行打蜡
				car.waitingWax();
				TimeUnit.MILLISECONDS.sleep(200);
				// 抛光
				car.buffered();
			} catch (InterruptedException e) {
				System.out.println("抛光被中断");
			}
		}
		System.out.println("抛光结束");
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
}

/*
 * In fact, the only place you can call wait( ), notify( ), or notifyAll( ) is
 * within a synchronized method or block (sleep( ) can be called within
 * non-synchronized methods since it doesn’t manipulate the lock). If you call
 * any of these within a method that’s not synchronized, the program will
 * compile, but when you run it, you’ll get an IllegalMonitorStateException with
 * the somewhat nonintuitive message "current thread not owner." Thismessage
 * means that the task calling wait( ), notify( ), or notifyAll( ) must "own"
 * (acquire) the lock for the object before it can call any of those methods.
 */
class Car {
	// wasOn false:没有打蜡 true:已经打过蜡了,可以进行抛光
	private boolean	waxOn	= false;
	private String	name;
	
	public Car(String name) {
		// 初始化的时候说明已经做完了打蜡的动作
		waxOn = true;
		this.name = name;
	}
	
	public synchronized void waxOn() throws InterruptedException {
		// 执行完打蜡的步骤,然后去唤醒等待抛光的线程
		waxOn = true;
		TimeUnit.MILLISECONDS.sleep(100);
		notifyAll();
		System.out.println(this + "Wax finished");
	}
	
	public synchronized void buffered() throws InterruptedException {
		// 抛光后去唤醒等待打蜡的线程
		waxOn = false;
		TimeUnit.MILLISECONDS.sleep(100);
		notifyAll();
		System.out.println(this + "Buff finished");
	}
	
	public synchronized void waitingWax() throws InterruptedException {
		// 等待打蜡,只有车子抛光后才可以打蜡,否则就只有等待
		// System.out.println(this + " waiting Wax....");
		while (!waxOn) {
			wait();
		}
	}
	
	public synchronized void waitingBuffered() throws InterruptedException {
		// 等待抛光,只有在车子打完蜡之后才可以抛光,否则就只有等待
		// System.out.println(this + " waiting Buff....");
		while (waxOn) {
			wait();
		}
		
	}
	
	public boolean isWaxOn() {
		return waxOn;
	}
	
	public void setWaxOn(boolean waxOn) {
		this.waxOn = waxOn;
	}
	
	@Override
	public String toString() {
		
		return name + " " + waxOn + " ";
	}
	
}
