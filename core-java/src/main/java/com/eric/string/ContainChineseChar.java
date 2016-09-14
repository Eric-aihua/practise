package com.eric.string;

public class ContainChineseChar {
	public static void main(String[] args) {
		String chineseStr = "中华人民公社";
		char[] charArray = chineseStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
				System.out.println(charArray[i]);
			}
		}
	}
}
