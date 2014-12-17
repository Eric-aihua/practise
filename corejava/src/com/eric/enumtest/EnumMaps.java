package com.eric.enumtest;

import static com.eric.enumtest.AlarmPoints.BATHROOM;
import static com.eric.enumtest.AlarmPoints.OFFICE1;
import static com.eric.enumtest.AlarmPoints.OFFICE2;
import static com.eric.enumtest.AlarmPoints.OFFICE3;

import java.util.EnumMap;

/*
 * Here’s an example that demonstrates the use of the Command  design pattern. This pattern starts 
 with an interface containing (typically) a sing le method, and creates multiple implementations 
 with different behavior for that method. You install Command objects, and your program calls 
 them when necessary:
 * */
public class EnumMaps {
	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> em = initMap();
		for (AlarmPoints ap : em.keySet()) {
			System.out.print("\n ap's action is:");
			em.get(ap).action();
		}
		
	}
	
	private static EnumMap<AlarmPoints, Command> initMap() {
		EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
		em.put(BATHROOM, new Command() {
			public void action() {
				System.out.println(BATHROOM);
			}
		});
		em.put(OFFICE1, new Command() {
			public void action() {
				System.out.println(OFFICE1);
			}
		});
		em.put(OFFICE2, new Command() {
			public void action() {
				System.out.println(OFFICE1);
			}
		});
		em.put(OFFICE3, new Command() {
			public void action() {
				System.out.println(OFFICE3);
			}
		});
		return em;
	}
}
