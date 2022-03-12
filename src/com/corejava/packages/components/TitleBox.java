package com.corejava.packages.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.Border;

public class TitleBox {
	private JTextField textField = new JTextField();
	private String title;
	private Border border;
	private Color textColor;
	private Font font;

	public TitleBox(String title, Border border, Color textColor, Font font) {
		this.title = title;
		this.border = border;
		this.textColor = textColor;
		this.font = font;
	}

	public JTextField Generate() {
		SetTitleBox(title);
		textField.setBorder(border);
		textField.setForeground(textColor);
		textField.setFont(font);
		textField.setEditable(false);
		return textField;
	}

	public void SetTitleBox(String noteName) {
		ClearAll();
		textField.setText(noteName);
	}

	public void ClearAll() {
		textField.setText(null);
	}

	/**
	 * @return JTextField return the topicNameArea
	 */
	public JTextField getTopicNameArea() {
		return textField;
	}

	/**
	 * @param topicNameArea the topicNameArea to set
	 */
	public void setTopicNameArea(JTextField topicNameArea) {
		this.textField = topicNameArea;
	}

}
