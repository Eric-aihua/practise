package com.eric.concurrency.completionservice;

/**
 * 模拟一个简单的线程，这个线程执行一次run任务需要1000毫秒,且这个任务一直在运行
 * @author aihua.sun
 *
 */
public class SimpleRunnable implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(2000);
				System.out.print("线程:"+Thread.currentThread().getName()+"处理一次任务完成"+"\n");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
