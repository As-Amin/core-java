package com.corejava.packages.home;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;

import com.corejava.packages.colors.DarkModeColors;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

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
