package com.corejava.packages.ui;

import javax.swing.JTextField;

public class TextBox extends JTextField {

	public TextBox(String text, Boolean editable) {
		this.setText(text);
		this.setEditable(editable);
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
