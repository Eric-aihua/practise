/**
 * 
 */
package com.eric.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 主要是用Semaphore 实现的一个DB pool A normal lock (from concurrent.locks or the
 * built-in synchronized lock) only allows one task at a time to access a
 * resource. A counting semaphore allows n tasks to access the resource at the
 * same time. You can also think of a semaphore as handing out "permits" to use
 * a resource, although no actualpermit objects are used.
 * 
 * @author Eric
 * 
 */
public class SemaphoreDBPool<T> {
	private volatile List<T>	objectList	= new ArrayList<T>();
	private volatile boolean[]	used;
	private int	               size;
	private Semaphore	       semaphore;
	
	// this pool was used to keep the type of object,and it's size is 'size'
	public SemaphoreDBPool(int size, Class<T> type) {
		try {
			// Semaphore could keep some permits for handler
			
			semaphore = new Semaphore(size);
			this.size = size;
			used = new boolean [size];
			for (int i = 0; i < size; i++) {
				objectList.add(type.newInstance());
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	// use synchronized to protected operation atom
	public synchronized Object getConn() throws InterruptedException {
		for (int i = 0; i < size; i++) {
			if (!used[i]) {
				// use semaphore to control access connection
				semaphore.acquire();
				// to indicate this connection was used
				used[i] = true;
				Object connection = objectList.get(i);
				System.out.println(connection + " was used");
				return connection;
			}
		}
		return null;
	}
	
	// return back connection object to pool and release permits to semaphore
	public synchronized void releaseConn(Object obj) {
		System.out.println(obj + " was release");
		semaphore.release();
		int index = objectList.indexOf(obj);
		used[index] = false;
	}
	
	public List<T> getObjectList() {
		return objectList;
	}
	
	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}
	
	public boolean[] getUsed() {
		return used;
	}
	
	public void setUsed(boolean[] used) {
		this.used = used;
	}
	
}
