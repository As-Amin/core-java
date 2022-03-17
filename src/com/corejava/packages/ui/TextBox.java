package com.corejava.packages.ui;

import javax.swing.JTextField;

public class TextBox extends JTextField {
	private String text;

	public TextBox(String text, Boolean editable) {
		this.text = text;
		this.setEditable(editable);
	}

	public JTextField generate() {
		setText(text);
		return this;
	}

	public void clearAll() {
		this.setText(null);
	}

	/**
	 * @return JTextField return the string
	 */
	public JTextField getTextField() {
		return this;
	}
}
