package com.eric.array;

import java.util.Arrays;

public class ParameterizedArrayType {
	public static void main(String[] args) {
		Integer[] ints = { 1, 2, 3, 4, 5};
		Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5};
		Integer[] array1 = new ClassParameter<Integer>().getParameterArray(ints);
		System.out.println(Arrays.toString(array1));
		Double[] array2 = new ClassParameter<Double>().getParameterArray(doubles);
		System.out.println(Arrays.toString(array2));
		
		Integer[] array3 = new MethodParameter().getParameters(ints);
		System.out.println(Arrays.toString(array3));
		Double[] array4 = new MethodParameter().getParameters(doubles);
		System.out.println(Arrays.toString(array4));
		
	}
	
}

class ClassParameter<T> {
	public T[] getParameterArray(T[] ts) {
		return ts;
	}
}

class MethodParameter {
	public <T> T[] getParameters(T[] ts) {
		return ts;
	}
}
