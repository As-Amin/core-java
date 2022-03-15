package com.corejava.packages.ui;

import javax.swing.JLabel;

public class TextBox {
	private String text;
	private JLabel textLabel = new JLabel();

	public TextBox(String text) {
		this.text = text;
	}

	public JLabel Generate() {
		setText(text);
		return textLabel;
	}

	public void ClearAll() {
		textLabel.setText(null);
	}

	/**
	 * @return String return the text
	 */
	public String getText() {
		return text;
	}

	public void setText(String newText) {
		ClearAll();
		textLabel.setText(" " + newText);
	}

	/**
	 * @return JLabel return the textLabel
	 */
	public JLabel getTextLabel() {
		return textLabel;
	}
}
