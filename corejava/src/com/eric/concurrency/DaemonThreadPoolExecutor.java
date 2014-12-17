package com.eric.concurrency;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * create personalize ThreadPoolExecutor
 * 
 * @author Eric
 * 
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
	public DaemonThreadPoolExecutor() {
		super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DaemonThreadFactory());
	}
	
	public static void main(String[] args) throws InterruptedException {
		new DaemonThreadPoolExecutor().execute(new SimpleDaemons());
		TimeUnit.MILLISECONDS.sleep(1000);
		
	}
}

/*
 * 
 * History:
 * 
 * 
 * 
 * $Log: $
 */