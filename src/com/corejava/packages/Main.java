package com.corejava.packages;

import java.awt.Color;
import java.util.ArrayList;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import com.corejava.packages.screens.Home;
import com.corejava.packages.screens.Login;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

public class Main {
	public static final String IMAGES_DIRECTORY = "./src/resources/images";
	public static final String TOPICS_DIRECTORY = "./src/resources/topics";

	public static String THEME_CONFIG_DIRECTORY =
			"./src/com/corejava/packages/themes/FlatDarkLaf.properties";
	public static final String APP_CONFIG_DIRECTORY = "./src/config/config.properties";

	public static PropertiesConfiguration THEME_CONFIG_OBJECT;
	public static PropertiesConfiguration APP_CONFIG_OBJECT = null;

	public static String APP_NAME;
	public static String APP_SLOGAN;
	public static String APP_VERSION;

	public static Color ACCENT_COLOR;
	public static Color SECONDARY_ACCENT_COLOR;

	public static void main(String[] args) {
		try {
			FlatLaf.registerCustomDefaultsSource("com.corejava.packages.themes");
			THEME_CONFIG_OBJECT = new PropertiesConfiguration(THEME_CONFIG_DIRECTORY);
			// Set the accent color and secondary accent color so text colours can be changed
			ACCENT_COLOR = Color.decode(THEME_CONFIG_OBJECT.getProperty("@accentColor").toString());
			SECONDARY_ACCENT_COLOR = Color
					.decode(THEME_CONFIG_OBJECT.getProperty("@secondaryAccentColor").toString());

			APP_CONFIG_OBJECT = new PropertiesConfiguration(APP_CONFIG_DIRECTORY);
			APP_NAME = APP_CONFIG_OBJECT.getProperty("app.name").toString();
			APP_SLOGAN = APP_CONFIG_OBJECT.getProperty("app.slogan").toString();
			APP_VERSION = APP_CONFIG_OBJECT.getProperty("app.version").toString();
		} catch (ConfigurationException ce) {
			ce.printStackTrace();
		}

		try {
			FlatDarkLaf.setup();
			@SuppressWarnings("unused")
			// Login loginScreen = new Login();
			Home homeScreen = new Home();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

