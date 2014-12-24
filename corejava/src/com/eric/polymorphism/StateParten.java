package com.eric.polymorphism;

abstract class Weather {
	public abstract void info();
	public Weather(){}
}

class Sunshine extends Weather {
	public void info() {
		System.out.println("good weather...");
	}
}

class Cloudy extends Weather {
	public void info() {
		System.out.println("bad weather..");
	}
}

public class StateParten {
	private Weather	weather;
	
	public StateParten(Weather weather) {
		this.weather = weather;
	}
	
	public void weatherInfo() {
		weather.info();
	}
	
	public void change() {
		if (this.weather.getClass().getName().equals("Sunshine")) {
			weather = new Cloudy();
		} else {
			weather = new Sunshine();
		}
	}
	
	public static void main(String[] args) {
		//Weather weather=new Weather();
		StateParten sp = new StateParten(new Cloudy());
		sp.weatherInfo();
		sp.change();
		sp.weatherInfo();
		
	}
}
