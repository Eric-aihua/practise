package com.eric.io;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesDemo {
	public static void main(String[] args) throws BackingStoreException {
		Preferences preferences = Preferences.userNodeForPackage(PreferencesDemo.class);
		preferences.put("name", "eric");
		//preferences.putInt("age", 15);
		for (String string : preferences.keys()) {
			System.out.println(preferences.get(string, null));
		}
	}
}
