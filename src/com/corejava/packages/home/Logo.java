package com.corejava.packages.home;

import java.awt.Font;

import javax.swing.JLabel;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

public class Logo {
	private JLabel logoLabel;
	
	public JLabel Generate() {
		logoLabel = new JLabel(' ' + Main.getAppName());
		logoLabel.setForeground(Colors.DARK3_THEME_COLOR.getColor());
		logoLabel.setFont(new Font(FN.LOGO.getFN(), Font.BOLD, FS.SIDE_LOGO.getFS()));
		return logoLabel;
	}
}
