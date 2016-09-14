package com.eric.array;

import java.util.Arrays;

public class MultidimensionalPrimitiveArray {
	public static void main(String[] args) {
		// int[][] multiArray=new int[][];
		// int[][] multiArray=new int[][4];
		int[][] multiArray = { { 1, 2, 3, 4}, { 3, 4}, { 5, 3}, { 1,}, {}};
		// [[I@6bbc4459, [I@152b6651, [I@544a5ab2, [I@5d888759, [I@2e6e1408]
		System.out.println(Arrays.toString(multiArray));
		// multi array need to use deepToString to print array
		System.out.println(Arrays.deepToString(multiArray));
		boolean[][][] threeMensional = new boolean [5] [5] [5];
		System.out.println(Arrays.deepToString(threeMensional));
		
		BerylliumSphere[][] spheres = {
		        { new BerylliumSphere(), new BerylliumSphere()},
		        { new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()},
		        { new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere(),
		                new BerylliumSphere()},};
		System.out.println(Arrays.deepToString(spheres));
	}
}
