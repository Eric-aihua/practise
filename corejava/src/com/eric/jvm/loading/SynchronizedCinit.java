package com.eric.jvm.loading;

/**
 * 用来证明Cinit方法是线程安全的
 * */
public class SynchronizedCinit {
	public static void main(String args[]) {
		Runnable deadLoading = new Runnable() {
			public void run() {
				System.out.println("begin");
				DeadLoopInit d1 = new DeadLoopInit();
				System.out.println("end");

			}
		};
		new Thread(deadLoading).start();
		new Thread(deadLoading).start();
	}
}

class DeadLoopInit {
	static {
		if (true) {
			System.out.println(Thread.currentThread() + " Initizlize:"
					+ System.currentTimeMillis());
			while (true) {
			}
		}
	}
}
