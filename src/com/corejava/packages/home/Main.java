package com.corejava.packages.home;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

public class Main {
	private static final String APP_NAME = "CoreJava";
	private static final String DIRECTORY = "./topics";

	public static void main(String[] args) {
		FlatLaf.registerCustomDefaultsSource("com.corejava.packages.themes");
		FlatDarkLaf.install();

		try {
			@SuppressWarnings("unused")
			HomeScreen homeScreen = new HomeScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getAppName() {
		return APP_NAME;
	}

	public static String getDirectory() {
		return DIRECTORY;
	}

}
