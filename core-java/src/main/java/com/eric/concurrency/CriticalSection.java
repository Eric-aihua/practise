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
import java.util.concurrent.atomic.AtomicLong;

/**
 * 本类主要说明对整个方法进行同步,以及临界区同步的效率对比
 * 
 * @author Eric
 * 
 */
public class CriticalSection {
	public static void main(String[] args) throws InterruptedException {
		PairManager1 pm1 = new PairManager1();
		PairManager2 pm2 = new PairManager2();
		
		PairManipulator pairManipulator1 = new PairManipulator(pm1);
		PairManipulator pairManipulator2 = new PairManipulator(pm2);
		
		PairChecker pc = new PairChecker(pm1);
		
		ExecutorService es1 = Executors.newCachedThreadPool();
		// 下面是对两种increment的执行效率对比,为了减少线程间的影响,所以选择一个一个的执行
		//es1.execute(pairManipulator1);// 368236,391182,372664
		 es1.execute(pairManipulator2); //: 788819,670206,786809,:695163
		es1.execute(pc);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted");
		}
		System.out.println("pm1:" + pm1 + " pm2:" + pm2);
		System.exit(0);
	}
}

class ValueNotEqualException extends Exception {
	/**
     * 
     */
	private static final long	serialVersionUID	= -13436662132684858L;
	
	public ValueNotEqualException() {
		super();
	}
}

// Pair have two field, and that's value must be equal
class Pair {
	private int	x;
	private int	y;
	
	public Pair() {}
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void checkValue() throws ValueNotEqualException {
		if (x != y) {
			throw new ValueNotEqualException();
		}
	}
	
	public void incrementX() {
		x++;
	}
	
	public void incrementY() {
		y++;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "pair:" + x;
	}
	
}

abstract class PariManager {
	protected Pair	     pair	= new Pair();
	private List<Pair>	 pairs	= Collections.synchronizedList(new ArrayList<Pair>());
	protected AtomicLong	al	= new AtomicLong(0);
	
	public synchronized Pair getPair() {
		return new Pair(pair.getX(), pair.getY());
	}
	
	// 在每次调用该方法的时候都会让al加一
	public abstract void increment();
	
	public void store(Pair pair) {
		pairs.add(pair);
	}
	
	public String toString() {
		return "pair:" + pair + " size:" + al.toString();
	}
}

// 对整个方法进行同步
class PairManager1 extends PariManager {
	
	@Override
	public synchronized void increment() {
		pair.incrementX();
		pair.incrementY();
		store(getPair());
		
	}
	
}

// 局部同步
class PairManager2 extends PariManager {
	
	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			
			pair.incrementX();
			pair.incrementY();
			temp = getPair();
			
		}
		store(pair);
		
	}
	
}

// 调用执行类
class PairManipulator implements Runnable {
	PariManager	pm;
	
	public PairManipulator(PariManager pm) {
		this.pm = pm;
	}
	
	@Override
	public void run() {
		while (true) {
			pm.increment();
			
		}
	}
	
}

// Pair的检查类
class PairChecker implements Runnable {
	PariManager	pm;
	
	public PairChecker(PariManager pm) {
		this.pm = pm;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				pm.getPair().checkValue();
				pm.al.incrementAndGet();
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (Exception e) {
				System.out.println(pm);
			}
		}
	}
}
