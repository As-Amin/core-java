package com.corejava.packages.ui;

import javax.swing.JButton;

public class Button extends JButton {
	private String text;

	public Button(String text) {
		this.text = text;
	}

	public JButton generate() {
		setText(text);
		return this;
	}

	/**
	 * @return String return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return JButton return the button
	 */
	public JButton getButton() {
		return this;
	}
}
