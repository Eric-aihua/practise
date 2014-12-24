package com.eric.exception;

import java.util.logging.Logger;

public class LogTest {
	public static void main(String[] args) {
		Logger log=Logger.getLogger("com.xyz.foo");
		log.info("log test");
		Logger.global.info("global info");
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 10000; j++) {
				int a=i*j;
			}
		}
	}
}
