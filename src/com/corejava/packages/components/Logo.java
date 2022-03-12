package com.corejava.packages.components;

import java.awt.Font;

import javax.swing.JLabel;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.home.Main;

public class Logo {
	private JLabel logoLabel;
	private int size;

	public Logo(int size) {
		this.size = size;
	}

	public JLabel Generate() {
		logoLabel = new JLabel(" " + Main.APP_NAME);
		logoLabel.setForeground(Colors.THEME.getColor());
		logoLabel.setFont(new Font(FN.CONSOLAS.getFN(), Font.BOLD, size));
		return logoLabel;
	}

	/**
	 * @return JLabel return the logoLabel
	 */
	public JLabel getLogoLabel() {
		return logoLabel;
	}

	/**
	 * @param logoLabel the logoLabel to set
	 */
	public void setLogoLabel(JLabel logoLabel) {
		this.logoLabel = logoLabel;
	}

	/**
	 * @return int return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
