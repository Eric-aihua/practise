package com.eric.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FillingLists {
	public static void main(String[] args) {
		List<StringAddress> address = Arrays.asList(new StringAddress("SH"), new StringAddress("GZ"), new StringAddress("NJ"),
		        new StringAddress("WH"));
		System.out.println(fillList(address,new StringAddress("SH")));
		Collections.nCopies(100, new StringAddress("NEW"));
	}
	
	public static <T> List<T> fillList(List<T> list,T t) {
		Collections.fill(list, t);
		return list;
	}
}

class StringAddress {
	private String	address;
	
	public StringAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return "address:" + super.toString();
	}
}
