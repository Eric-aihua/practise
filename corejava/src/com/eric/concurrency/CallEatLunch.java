package com.eric.concurrency;

import java.awt.Toolkit;
import java.util.GregorianCalendar;

public class CallEatLunch {
	public static void main(String[] args) {
		Thread thread = new Thread(new Call());
		thread.start();
	}
}
class Call implements Runnable {
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			int year = gregorianCalendar.get(GregorianCalendar.YEAR);
			int month = gregorianCalendar.get(GregorianCalendar.MONTH);
			int day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
			int hour = gregorianCalendar.get(GregorianCalendar.HOUR);
			int minute = gregorianCalendar.get(GregorianCalendar.MINUTE);
			int second = gregorianCalendar.get(GregorianCalendar.SECOND);
			System.out.println(year + ":" + month + ":" + day + ":" + hour + ":" + minute + ":" + second);
			if ((hour == 5) && (minute == 28)) {
				for (int i = 0; i < 10; i++) {
					System.out.println("call eat");
					Toolkit   toolkit   =   toolkit   =   Toolkit.getDefaultToolkit();
					toolkit.beep();
				}
			}
		}
	}
}
