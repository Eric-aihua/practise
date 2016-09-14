package com.eric.exception;

import java.io.FileNotFoundException;

public class ThrowExceptionTest {
	public static void main(String[] args) throws NotFoundException {
		System.out.println("begin test");
		Throw t = new Throw();
		t.ThrowText();
		System.out.println("test end!");
	}
}

class NotFoundException extends FileNotFoundException {
	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}
}

class Throw {
	// public void ThrowText() throws NotFoundException{
	@SuppressWarnings("finally")
	public void ThrowText() throws NotFoundException {
		System.out.println("begin throw!");
		String a="erci";
		try {
			throw new NotFoundException("not found");
		} catch (NotFoundException e) {
			Throwable t = new NotFoundException("data");
			a=null;
		} finally {
			System.out.println(a.length());
			System.out.println("begin finally");
		}

	}
}
