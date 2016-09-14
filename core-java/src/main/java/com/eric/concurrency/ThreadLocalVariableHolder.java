package com.eric.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本类主要掩饰通过ThreadLocal来作为共享变量,且安全的被访问
 * 因为ThreadLocal会在每个thread中生成一个副本,所以线程之间不会进行相互影响
 * 下面的结果是没有用ThreadLocal的结果,从结果中可以看到 两个线程共享了一个变量 id:0 value:59 id:1 value:59 id:1
 * value:61 id:1 value:62 id:0 value:60 id:1 value:63 id:0 value:64 id:1
 * value:65 id:0 value:66 id:1 value:67 id:0 value:68 id:1 value:69 id:0
 * value:70 id:1 value:71 id:0 value:72 id:1 value:73 id:0 value:74 id:1
 * value:75 id:0 value:76 id:1 value:77 id:0 value:78 id:1 value:79 id:0
 * value:80 id:1 value:81 id:0 value:82 id:1 value:83 id:0 value:84 id:1
 * value:85 id:0 value:86 id:1 value:87 id:0 value:88 id:1 value:89 id:0
 * value:90 id:1 value:91 id:1 value:93 id:1 value:94 id:1 value:95 id:1
 * value:96 id:1 value:97 id:1 value:98 id:1 value:99 id:0 value:92 id:1
 * value:100
 * 
 * 使用本地线程的结果,两个线程使用了不同的变量,之间没有相互的影响 id:0 value:59 id:1 value:56 id:0 value:60
 * id:1 value:57 id:0 value:61 id:1 value:58 id:1 value:59 id:1 value:60 id:1
 * value:61 id:0 value:62 id:1 value:62 id:0 value:63 id:1 value:63 id:0
 * value:64 id:1 value:64 id:0 value:65 id:1 value:65 id:0 value:66 id:1
 * value:66 id:0 value:67 id:1 value:67 id:0 value:68 id:1 value:68 id:0
 * value:69 id:1 value:69 id:1 value:70 id:1 value:71 id:1 value:72 id:1
 * value:73 id:1 value:74 id:1 value:75 id:1 value:76 id:1 value:77 id:0
 * value:70
 * 
 * @author Eric
 * 
 */
public class ThreadLocalVariableHolder {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++) {
			es.execute(new Accesor(i));
		}
		TimeUnit.MILLISECONDS.sleep(3);
		es.shutdownNow();
	}
	
	// 本地线程
	static ThreadLocal<Integer>	tl	= new ThreadLocal<Integer>()
	                               
	                               {
		                               Random	random	= new Random(47);
		                               
		                               public synchronized Integer initialValue() {
			                               return random.nextInt(100);
		                               }
	                               };
	
	// 非本地线程
	// static NotThreadLocal tl = new NotThreadLocal();
	
	class Counter {
		private int	id;
		
		public Counter() {
			
		}
	}
	
	public static Object get() {
		return tl.get();
	}
	
	public static void increment() {
		tl.set(tl.get() + 1);
	}
	
}

// 非ThreadLocal
class NotThreadLocal {
	private int	  num;
	static Random	random	= new Random(47);
	
	public NotThreadLocal() {
		this.num = random.nextInt(100);
	}
	
	public int get() {
		return num;
	}
	
	public void set(int obj) {
		this.num = (Integer) obj;
	}
	
}

class Accesor implements Runnable {
	private int	id;
	
	public Accesor(int id) {
		this.id = id;
	}
	
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}
	
	public String toString() {
		return "id:" + id + " value:" + ThreadLocalVariableHolder.get();
	}
}