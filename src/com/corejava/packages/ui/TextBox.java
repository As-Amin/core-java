package com.corejava.packages.ui;

import javax.swing.JLabel;

public class TextBox {
	private JLabel textLabel = new JLabel();
	private String text;

	public TextBox(String text) {
		this.text = text;
	}

	public JLabel Generate() {
		setText(text);
		return textLabel;
	}

	public void setText(String newText) {
		ClearAll();
		textLabel.setText(" " + newText);
	}

	public void ClearAll() {
		textLabel.setText(null);
	}

	/**
	 * @return JLabel return the textLabel
	 */
	public JLabel getTextLabel() {
		return textLabel;
	}

	/**
	 * @return String return the text
	 */
	public String getText() {
		return text;
	}
}
