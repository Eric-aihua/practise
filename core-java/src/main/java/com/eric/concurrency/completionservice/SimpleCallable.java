package com.eric.concurrency.completionservice;

import java.util.Random;
import java.util.concurrent.Callable;

// 模拟一个简单的可调用类，这个类执行一次任务需要1000毫秒才能算出结果
public class SimpleCallable implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		System.out.print("线程:"+Thread.currentThread().getName()+"处理一次任务完成"+"\n");
		return new Random().nextInt(1000);
	}
	
}
