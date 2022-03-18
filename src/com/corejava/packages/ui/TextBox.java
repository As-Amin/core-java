package com.corejava.packages.ui;

import javax.swing.JTextField;

public class TextBox extends JTextField {

	private String startingText;

	public TextBox(String startingText, String text, Boolean editable) {
		this.setEditable(editable);
		setText(startingText + text);
		this.startingText = startingText;
	}

	public void addToStartingText(String text) {
		if (startingText != null) {
			setText(startingText + text);
		}
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

	/**
	 * @return String return the startingText
	 */
	public String getStartingText() {
		return startingText;
	}

	/**
	 * @param startingText the startingText to set
	 */
	public void setStartingText(String startingText) {
		this.startingText = startingText;
	}

}
