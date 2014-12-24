/**
 * 
 */
package com.eric.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本类是用来模拟公园有很多个入口,每次游客从入口进入的时候,会在该入口的统计数目中进行累计,同时也会在整个公园的的总数中进行累计,到最后,
 * 公园的总数要等于所有入口人数的总和
 * 
 * @author Eric.sun
 * 
 */

public class OrnamentalGarden {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			
			es.execute(new Entrance(i));
		}
		es.shutdown();
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		Entrance.getTotalNumberByEntrances();
		Entrance.getTotalByCount();
		
	}
}

// 此类代表一个入口
class Entrance implements Runnable {
	private static List<Entrance>	entrances	= Collections.synchronizedList(new ArrayList<Entrance>());
	private int	                    number	  = 0;
	private static Count	        count	  = new Count();
	private static volatile boolean	cancel	  = false;
	private int	                    id;
	
	public Entrance() {}
	
	public Entrance(int id) {
		this.id = id;
		entrances.add(this);
	}
	
	public static void cancel() {
		cancel = true;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String toString() {
		return "ENTRANCE:" + id + " COUNT:" + number;
	}
	
	public void run() {
		
		while (!cancel) {
			synchronized (this) {
				
				number++;// first to add this entrance number
				count.increment();// to add total number of customer
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static synchronized void getTotalNumberByEntrances() {
		int temp = 0;
		for (Entrance entrance : entrances) {
			temp += entrance.getNumber();
			System.out.println(entrance);
		}
		System.out.println(temp);
	}
	
	public static void getTotalByCount() {
		System.out.println("Totaled by Count:" + count.getCount());
	}
}

// 统计人数的类
class Count {
	private int	num;
	
	public synchronized void increment() {
		num++;
	}
	
	public synchronized int getCount() {
		return num;
	}
}
