/**
 * 本来主要用来说明用BlockQueue 用来做线程之间的协作.为了掩饰blockqueue的特性,用了面包的例子要想吃到面包,必须经过三个步骤,烤->吐奶油->涂酱
 */
package com.eric.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric
 * 
 */

public class ToastOMatic {
	public static void main(String[] args) {
		try {
			LinkedBlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<Toast>();
			LinkedBlockingQueue<Toast> bufferedQueue = new LinkedBlockingQueue<Toast>();
			LinkedBlockingQueue<Toast> jamesQueue = new LinkedBlockingQueue<Toast>();
			ExecutorService es = Executors.newCachedThreadPool();
			es.execute(new DryHandler(dryQueue));
			es.execute(new BufferedHandler(dryQueue, bufferedQueue));
			es.execute(new JamesHandler(bufferedQueue, jamesQueue));
			es.execute(new EatHandler(jamesQueue));
			TimeUnit.SECONDS.sleep(5);
			es.shutdownNow();
		} catch (InterruptedException e) {
			System.out.println("exit poast!");
		}
	}
}

class DryHandler implements Runnable {
	private LinkedBlockingQueue<Toast>	dryQueue;
	
	public DryHandler(LinkedBlockingQueue<Toast> dryQueue) {
		this.dryQueue = dryQueue;
	}
	
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Toast toast = new Toast();
				System.out.println("dry:" + toast);
				dryQueue.put(toast);
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("Dry Handler was interrupted!");
				System.exit(1);
			}
		}
	}
}

class BufferedHandler implements Runnable {
	private LinkedBlockingQueue<Toast>	dryQueue;
	private LinkedBlockingQueue<Toast>	bufferedQueue;
	
	public BufferedHandler(LinkedBlockingQueue<Toast> dryQueue, LinkedBlockingQueue<Toast> bufferedQueue) {
		super();
		this.dryQueue = dryQueue;
		this.bufferedQueue = bufferedQueue;
	}
	
	public void run() {
		while (!Thread.interrupted()) {
			
			try {
				Toast toast = dryQueue.take();
				toast.buffered();
				System.out.println("buffered:" + toast);
				bufferedQueue.put(toast);
			} catch (InterruptedException e) {
				System.out.println("Buffered was interrupted!");
			}
		}
	}
}

class JamesHandler implements Runnable {
	private LinkedBlockingQueue<Toast>	bufferedQueue;
	private LinkedBlockingQueue<Toast>	jamesQueue;
	
	public JamesHandler(LinkedBlockingQueue<Toast> bufferedQueue, LinkedBlockingQueue<Toast> jamesQueue) {
		super();
		this.bufferedQueue = bufferedQueue;
		this.jamesQueue = jamesQueue;
	}
	
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Toast toast = bufferedQueue.take();
				toast.james();
				System.out.println("james:" + toast);
				jamesQueue.put(toast);
			} catch (InterruptedException ex) {
				System.out.println("James was interrupted!");
			}
		}
	}
}

class EatHandler implements Runnable {
	private LinkedBlockingQueue<Toast>	jamesQueue;
	
	public EatHandler(LinkedBlockingQueue<Toast> jamesQueue) {
		super();
		this.jamesQueue = jamesQueue;
	}
	
	public void run() {
		while (!Thread.interrupted()) {
			try {
				Toast toast = jamesQueue.take();
				if (toast.getStatus() != ToastStatus.JAMES) {
					System.out.println("ERROR STATUS:" + toast);
					System.exit(1);
				} else {
					System.out.println("eat:" + toast);
				}
				
			} catch (InterruptedException ex) {
				System.out.println("James was interrupted!");
			}
		}
	}
}

enum ToastStatus {
	DRY, BUFFERED, JAMES
}

class Toast {
	private static int	count	= 0;
	private ToastStatus	status;
	
	public Toast() {
		count++;
		this.status = ToastStatus.DRY;
	}
	
	public void buffered() {
		this.status = ToastStatus.BUFFERED;
	}
	
	public void james() {
		this.status = ToastStatus.JAMES;
	}
	
	@Override
	public String toString() {
		return "Toast :" + count + " status=" + status + ":";
	}
	
	public ToastStatus getStatus() {
		return status;
	}
	
	public void setStatus(ToastStatus status) {
		this.status = status;
	}
	
}