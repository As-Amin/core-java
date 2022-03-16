package com.corejava.packages.ui;

import javax.swing.JTextField;

public class TextBox {
	private String text;
	private JTextField textField = new JTextField();

	public TextBox(String text, Boolean editable) {
		this.text = text;
		textField.setEditable(editable);
	}

	public JTextField Generate() {
		setText(text);
		return textField;
	}

	public void ClearAll() {
		textField.setText(null);
	}

	public void setText(String newText) {
		ClearAll();
		textField.setText(newText);
	}

	public void appendText(String newText) {
		String oldText = textField.getText();
		ClearAll();
		textField.setText(oldText + newText);
	}

	/**
	 * @return JTextField return the textLabel
	 */
	public JTextField getTextField() {
		return textField;
	}
}
