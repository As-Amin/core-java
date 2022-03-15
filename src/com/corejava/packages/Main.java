package com.corejava.packages;

import com.corejava.packages.home.Home;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Main {
	public static final String APP_NAME = "CoreJava";
	public static final String APP_SLOGAN = "Learn the fundamentals";

	public static final String IMAGES_DIRECTORY = "./res";
	public static final String TOPICS_DIRECTORY = "./topics";

	public static String THEME_CONFIG_DIRECTORY =
			"./src/com/corejava/packages/themes/FlatDarkLaf.properties";
	public static final String APP_CONFIG_DIRECTORY = "./src/config/config.properties";

	public static PropertiesConfiguration APP_CONFIG;

	public static void main(String[] args) throws ConfigurationException {
		FlatLaf.registerCustomDefaultsSource("com.corejava.packages.themes");

		APP_CONFIG = new PropertiesConfiguration(Main.APP_CONFIG_DIRECTORY);
		if (APP_CONFIG.getProperty("app.theme").equals("DarkTheme")) {
			Main.THEME_CONFIG_DIRECTORY =
					"./src/com/corejava/packages/themes/FlatDarkLaf.properties";
			FlatDarkLaf.setup();
		} else if (APP_CONFIG.getProperty("app.theme").equals("LightTheme")) {
			Main.THEME_CONFIG_DIRECTORY =
					"./src/com/corejava/packages/themes/FlatLightLaf.properties";
			FlatLightLaf.setup();
		} else {
			Main.THEME_CONFIG_DIRECTORY =
					"./src/com/corejava/packages/themes/FlatDarkLaf.properties";
			FlatDarkLaf.setup();
		}

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

