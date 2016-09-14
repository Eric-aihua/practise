package com.eric.collections;

import java.util.Map;

public class Envirment {
	public static void main(String[] args) {
		for (Map.Entry entry : System.getenv().entrySet()) {
			System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
		}
	}
}
