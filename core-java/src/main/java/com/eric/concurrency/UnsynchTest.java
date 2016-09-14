package com.eric.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnsynchTest {
	private static final int count=100;
	private static final double salary=1000;
	public static void main(String[] args) {
		Bank b=new Bank(count,salary);
//		for (int i = 0; i < count; i++) {
////		TransferRunnable r= new TransferRunnable(b,i,salary);
////		Thread t=new Thread(r);
////		t.start();
////		}
		TransferRunnable r= new TransferRunnable(b,(int)Math.random()*count,salary);
		Thread t=new Thread(r);
		t.start();
	}
}
class Bank{
	private final double[] accounts;
	private Lock bankLock=new ReentrantLock();
	public Bank(int count,double initBalance){
		accounts=new double[count];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i]=initBalance;
		}
	}
	
	public void transfer(int a,int b,double amount){
		bankLock.lock();
		try{
			
		System.out.println("total amount:"+getTotalAmount());
		System.out.println("begin transfer:");
		System.out.println(Thread.currentThread());
//		if(accounts[a]<amount){
//			return;
//		}
		System.out.println("begin a:"+accounts[a]+"  b:"+accounts[b]);
		accounts[a]=accounts[a]-amount;
		//System.out.println("account[a]"+accounts[a]);
		accounts[b]=accounts[b]+amount;
		System.out.println("end a:"+accounts[a]+"  b:"+accounts[b]);
		}
		finally{
			bankLock.unlock();
		}
	}
	public double getTotalAmount(){
		double sumAmount=0;
		for (int i = 0; i < accounts.length; i++) {
			sumAmount+=accounts[i];
		}
		return sumAmount;
	}
	public int size(){
		return accounts.length;
	}
	
}

class TransferRunnable implements Runnable{
	private Bank b;
	private int fromAmount;
	private double amount;
	
	public TransferRunnable(Bank b, int fromAmount, double amount) {
		super();
		System.out.println("fromAmount...."+fromAmount);
		this.b = b;
		this.fromAmount = fromAmount;
		this.amount = amount;
	}

	public void run() {
		while(true){
		int toAmount=(int)Math.random()*b.size();
		double amountT=Math.random()*amount;
		System.out.println("amountT:"+amountT);
		b.transfer(fromAmount, toAmount, amountT);
		}
	}
	
}
