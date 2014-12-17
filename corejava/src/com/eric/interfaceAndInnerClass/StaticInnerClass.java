package com.eric.interfaceAndInnerClass;

public class StaticInnerClass {
	public static void main(String[] args) {
		double[] values=new double[10];
		for (int i = 0; i < values.length; i++) {
			values[i]=Math.random()*100;
			System.out.println(values[i]);
		}
		ArrayAlg.Pair pair=ArrayAlg.minMax(values);
		System.out.println(pair.getFrist());
		System.out.println(pair.getSecond());
		System.out.println(ArrayAlg.Pair.name);
		System.out.println(ArrayAlg.Pair.innerInnerClass.class.getName());
	}
}

class ArrayAlg {
	private  String name;
	static class Pair {
		private double frist;
		private double second;
		public static String name="eric";
		
		public Pair() {
			super();
		}

		public Pair(double frist, double second) {
			super();
			this.frist = frist;
			this.second = second;
		}

		public double getFrist() {
			return frist;
		}

		public void setFrist(double frist) {
			this.frist = frist;
		}

		public double getSecond() {
			return second;
		}

		public void setSecond(double second) {
			this.second = second;
		}

	static class innerInnerClass{
		
		}
	}
	public  static Pair minMax(double[] values){
		double min=Double.MIN_VALUE;
		double max=Double.MAX_VALUE;
		for (double d : values) {
			if(d>min) max=d;
			if(d<max) min=d;
		}
		return new Pair(min,max);
	}
}
