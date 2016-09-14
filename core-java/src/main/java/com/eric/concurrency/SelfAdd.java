package com.eric.concurrency;

public class SelfAdd {
	public static void main(String[] args) {
		Add a1=new Add();
		Thread t1=new Thread(a1);
		Add a2=new Add();
		Thread t2=new Thread(a1);
		Add a6=new Add();
		Thread t6=new Thread(a1);
		Add a3=new Add();
		Thread t3=new Thread(a1);
		Add a4=new Add();
		Thread t4=new Thread(a1);
		Add a5=new Add();
		Thread t5=new Thread(a1);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
	}
}
class Add implements Runnable{
	private  int num=0;
	public void run() {
		while(true){
		num++;
		System.out.println(Thread.currentThread()+"#####"+num);
			System.out.println(num);
			try {
				Thread.sleep((long) 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}