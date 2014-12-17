package com.eric.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class HashSetTest {
	public static void main(String[] args) {
		HashSet<String> words = new HashSet<String>();
		System.out.println("please input word:");
		Scanner in = new Scanner(System.in);
		int currentTime;
		int totalTime;
		while (in.hasNext()) {
			String word = in.next();
			words.add(word);
			currentTime = (int) System.currentTimeMillis();
		}
		Iterator it = words.iterator();
		for (int i = 0; i < words.size(); i++) {
			String word = (String) it.next();
			System.out.println(word);
		}
	}

}
