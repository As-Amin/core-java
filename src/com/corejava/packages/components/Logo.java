package com.corejava.packages.components;

import java.awt.Font;

import javax.swing.JLabel;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.home.Main;

public class Logo {
	private JLabel logoLabel;

	public JLabel Generate() {
		logoLabel = new JLabel(" " + Main.getAppName());
		logoLabel.setForeground(Colors.THEME.getColor());
		logoLabel.setFont(new Font(FN.CONSOLAS.getFN(), Font.BOLD, FS.SIDE_LOGO.getFS()));
		return logoLabel;
	}
}
