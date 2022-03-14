package com.corejava.packages.swing_components;

import javax.swing.JLabel;

public class TitleBox {
	private JLabel textLabel = new JLabel();
	private String title;

	public TitleBox(String title) {
		textLabel.putClientProperty("FlatLaf.style", "font: +7 $medium.font");
		this.title = title;
	}

	public JLabel Generate() {
		SetTitleBox(title);
		return textLabel;
	}

	public void SetTitleBox(String fileName) {
		ClearAll();
		textLabel.setText(fileName);
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
	 * @param textLabel the textLabel to set
	 */
	public void setTextLabel(JLabel textLabel) {
		this.textLabel = textLabel;
	}

	/**
	 * @return String return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
