package com.eric.concurrency;

public class ThreadTest {
	public static void main(String args[]){
		SaleTicket st1=new SaleTicket();
		Thread t1=new Thread(st1);
		SaleTicket st2=new SaleTicket();
		Thread t2=new Thread(st2);
		t1.start();
		t2.start();
	}
}
class SaleTicket implements Runnable{
	private  static int count=10;
	public synchronized void run(){
		while(count>0){
			count--;
			try{
				Thread.sleep(500);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			System.out.print("thread is "+Thread.currentThread());
			System.out.println(": count is:"+count);
		}
	}
}

