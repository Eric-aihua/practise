package com.eric.lession.testmemory;

import java.io.Serializable;

public class People implements Serializable{
	private String name;
	private int time;
	public People(String name, int time) {
		super();
		this.name = name;
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}
