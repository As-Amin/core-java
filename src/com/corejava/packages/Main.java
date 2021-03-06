package com.corejava.packages;

import java.awt.Color;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.corejava.packages.screens.Home;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;

public class Main {
	public static final String IMAGES_DIRECTORY = "./src/resources/images";
	public static final String TOPICS_DIRECTORY = "./src/resources/topics";

	public static String THEME_CONFIG_DIRECTORY =
			"./src/com/corejava/packages/themes/FlatDarkLaf.properties";
	public static final String APP_CONFIG_DIRECTORY = "./src/config/config.properties";

	public static PropertiesConfiguration THEME_CONFIG_OBJECT;
	public static PropertiesConfiguration APP_CONFIG_OBJECT;

	public static Color BACKGROUND_COLOR;
	public static Color SECONDARY_BACKGROUND_COLOR;
	public static Color ACCENT_COLOR;
	public static Color SECONDARY_ACCENT_COLOR;

	public static void main(String[] args) {
		try {
			FlatLaf.registerCustomDefaultsSource("com.corejava.packages.themes");
			FlatDarkLaf.setup();

			APP_CONFIG_OBJECT = new PropertiesConfiguration(APP_CONFIG_DIRECTORY);
			THEME_CONFIG_OBJECT = new PropertiesConfiguration(THEME_CONFIG_DIRECTORY);

			// Set the accent color and secondary accent color so text colours can be changed
			ACCENT_COLOR = Color.decode(THEME_CONFIG_OBJECT.getProperty("@accentColor").toString());
			SECONDARY_ACCENT_COLOR = Color
					.decode(THEME_CONFIG_OBJECT.getProperty("@secondaryAccentColor").toString());
			BACKGROUND_COLOR =
					Color.decode(THEME_CONFIG_OBJECT.getProperty("@background").toString());
			SECONDARY_BACKGROUND_COLOR = Color
					.decode(THEME_CONFIG_OBJECT.getProperty("@componentBackground").toString());

			@SuppressWarnings("unused")
			Home homeScreen = new Home();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

