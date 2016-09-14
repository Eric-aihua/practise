package com.eric.exception;

public class LinkException {
	public static void main(String[] args) {
		
		new LinkException().callThrowException();
	}
	public  void throwException(){
		throw new NullPointerException("null point!");
	}
	public void callThrowException(){
		
		try{
			throwException();
		}
		catch(Exception e){
			IllegalArgumentException ill=new IllegalArgumentException();
			ill.initCause(e);
			throw ill;
		}
	}
}

