package com.corejava.packages.ui;

import javax.swing.JTextField;

public class TextBox extends JTextField {

	public TextBox(String text, Boolean editable) {
		this.setEditable(editable);
		setText(text);
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
