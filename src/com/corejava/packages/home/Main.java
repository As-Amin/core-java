package com.corejava.packages.home;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

public class Main {
	private static final String APP_NAME = "CoreJava";
	private static final String TOPICS_DIRECTORY = "./topics";
	private static final String IMAGES_DIRECTORY = "./images";

	public static void main(String[] args) {
		FlatLaf.registerCustomDefaultsSource("com.corejava.packages.themes");
		FlatDarkLaf.install();
		try {
			@SuppressWarnings("unused")
			Home homeScreen = new Home();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getAppName() {
		return APP_NAME;
	}

	public static String getTopicsDirectory() {
		return TOPICS_DIRECTORY;
	}

	public static String getImagesDirectory() {
		return IMAGES_DIRECTORY;
	}
}

/*
 * public class ExampleClass { // Declare a private instance variable (double) private double
 * instanceVariable = 0.5; // Declare a public instance variable (boolean) public Boolean isInstance
 * = true;
 * 
 * public void ExampleMethod() { // Print the boolean variable to the console
 * System.out.println(isInstance); } }
 */
