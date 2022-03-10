package com.corejava.packages.home;

import java.awt.Dimension;
import java.awt.Toolkit;

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
			HomeScreen homeScreen = new HomeScreen();
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
