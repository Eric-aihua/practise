package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 这个程序主要用最简单的程序来说明线程之间的协作
 * 
 * @author Eric
 * 
 */
public class SimpleCooperation {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		Light light = new Light(false);
		TurnOff off = new TurnOff(light);
		TurnOn on = new TurnOn(light);
		es.execute(on);
		es.execute(off);
		
		TimeUnit.SECONDS.sleep(2);
		es.shutdownNow();
	}
}

class Light {
	// turn on=true
	// turn off=false
	private Boolean	lightSwitch	= false;
	
	public Light(boolean b) {
		this.lightSwitch = b;
	}
	
	public Boolean getLightSwitch() {
		return lightSwitch;
	}
	
	public void setLightSwitch(Boolean lightSwitch) {
		this.lightSwitch = lightSwitch;
	}
	
	public String toString() {
		String temp = lightSwitch ? "light" : "darkness";
		return temp;
	}
}

class TurnOff implements Runnable {
	private Light	light;
	
	public TurnOff(Light light) {
		this.light = light;
	}
	
	public synchronized void run() {
		try {
			while (true) {
				
				while (!light.getLightSwitch()) {
					wait();
				}
				light.setLightSwitch(false);
				System.out.println(light);
				TimeUnit.MILLISECONDS.sleep(10);
				notifyAll();
			}
		} catch (InterruptedException e) {
			System.out.println("was broken");
		}
	}
	
}

class TurnOn implements Runnable {
	private Light	light;
	
	public TurnOn(Light light) {
		this.light = light;
	}
	
	public synchronized void run() {
		try {
			while (true) {
				
				while (light.getLightSwitch()) {
					wait();
				}
				light.setLightSwitch(true);
				System.out.println(light);
				TimeUnit.MILLISECONDS.sleep(10);
				notifyAll();
			}
		} catch (InterruptedException e) {
			System.out.println("was broken");
		}
	}
	
}
