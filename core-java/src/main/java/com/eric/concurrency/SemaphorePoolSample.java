package com.eric.concurrency;

public class SemaphorePoolSample {
	public static void main(String[] args) throws InterruptedException {
		// initialize a db pool that's size is 10
		SemaphoreDBPool<SemaphoreConnection> dbpool = new SemaphoreDBPool<SemaphoreConnection>(10, SemaphoreConnection.class);
		for (int i = 0; i < 20; i++) {
			System.out.println(dbpool.getConn());
        }
	}
}
