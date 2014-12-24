/**
 * 
 */
package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 本程序主要用餐厅,厨师,服务员的例子来说明线程之间的协作
 * 
 * @author Eric
 * 
 */
public class Restaurant {
	private Meal	meal;
	Waitress	    waitress	= new Waitress(this);
	Chef	        chef	 = new Chef(this);
	ExecutorService	es	     = Executors.newCachedThreadPool();
	
	public Restaurant() {
		es.execute(chef);
		es.execute(waitress);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Restaurant();
	}
	
	public Meal getMeal() {
		return meal;
	}
	
	public void setMeal(Meal meal) {
		this.meal = meal;
	}
	
}

class Chef implements Runnable {
	private Restaurant	restaurant;
	private int	       count;
	
	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public synchronized void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.getMeal() != null) {
						wait();
					}
				}
				while (count == 10) {
					restaurant.es.shutdownNow();
				}
				Meal meal = new Meal(count++);
				System.out.println("厨师做菜:" + meal);
				synchronized (restaurant.waitress) {
					// 在厨师的对象上同步,设置菜为空,然后唤醒所有的厨师
					restaurant.setMeal(meal);
					restaurant.waitress.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(10);
			}
		} catch (InterruptedException e) {
			System.out.println("厨师被中断");
		}
	}
}

class Waitress implements Runnable {
	private Restaurant	restaurant;
	
	public Waitress(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.getMeal() == null) {
						wait();
					}
				}
				System.out.println("服务员传菜:" + restaurant.getMeal());
				synchronized (restaurant.chef) {
					// 在厨师的对象上同步,设置菜为空,然后唤醒所有的厨师
					restaurant.setMeal(null);
					restaurant.chef.notifyAll();
				}
				
				TimeUnit.MILLISECONDS.sleep(10);
			}
		} catch (InterruptedException e) {
			System.out.println("服务员被中断");
		}
	}
}

class Meal {
	private int	num;
	
	public Meal(int num) {
		this.num = num;
	}
	
	public String toString() {
		return "Meal:" + num;
	}
}
