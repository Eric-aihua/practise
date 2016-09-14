package com.eric.concurrency;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorSample {
	
	/**
	 * The ScheduledThreadPoolExecutor provides just the service necessary to
	 * solve the problem. Using either schedule( ) (to run a task once) or
	 * scheduleAtFixedRate( ) (to repeat a task at a regular interval), you set
	 * up Runnable objects to be executed at some time in the future.
	 * 
	 * @param args
	 */
	public static ScheduledThreadPoolExecutor	se	= new ScheduledThreadPoolExecutor(10);
	
	public static void main(String[] args) {
		
		ScheduledExecutorSample ss = new ScheduledExecutorSample();
		ss.schedule("begin");
		ss.fixedPeriodSchedule();
		ss.schedule("end");
		
	}
	
	class Schedule implements Runnable {
		private String	name;
		
		public Schedule(String name) {
			this.name = name;
		}
		
		public void run() {
			System.out.println("Schedule" + name);
		}
	}
	
	class FixedSchedule implements Runnable {
		public void run() {
			System.out.println("Fixed period schedule");
		}
	}
	
	public void schedule(String name) {
		// 设定值执行一次的runnable
		se.schedule(new Schedule(name), 0, TimeUnit.NANOSECONDS);
	}
	
	public void fixedPeriodSchedule() {
		// 设定可以循环执行的runnable
		se.scheduleAtFixedRate(new FixedSchedule(), 0, 1, TimeUnit.SECONDS);
	}
	
}
