package com.corejava.packages.home;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

public class Main {
	public static final String APP_NAME = "CoreJava";
	public static final String TOPICS_DIRECTORY = "./topics";
	public static final String IMAGES_DIRECTORY = "./images";

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
}

/*
 * public class ExampleClass { // Declare a private instance variable (double) private double
 * instanceVariable = 0.5; // Declare a public instance variable (boolean) public Boolean isInstance
 * = true;
 * 
 * public void ExampleMethod() { // Print the boolean variable to the console
 * System.out.println(isInstance); } }
 */

/*
 * public class ExampleClass { // Declare a static variable (String) static String staticStr =
 * "This is a static variable"; }
 * 
 * public class AnotherExampleClass { public void AnotherExampleMethod() { // Print the static
 * variable to the console System.out.println(ExampleClass.staticStr); } }
 */

