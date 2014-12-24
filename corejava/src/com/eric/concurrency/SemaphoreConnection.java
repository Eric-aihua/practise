package com.eric.concurrency;

public class SemaphoreConnection {
	private static int	count	= 0;
	private int	       id	  = count++;
	private long	   sum	  = 0l;
	
	public SemaphoreConnection() {
		// Simulated time-consuming operation
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
	}
	
	public String toString() {
		return "Con:" + id;
	}
}
