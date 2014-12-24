package com.eric.swing;
import java.awt.GraphicsEnvironment;
public class ListFont {
	public static void main(String[] args) {
		String listFont[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (String string : listFont) {
			System.out.println(string);
		}
	}
}
