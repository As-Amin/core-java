package com.corejava.packages.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

public class ScrollTextPane extends JScrollPane {
	private JTextPane textPane = new JTextPane();;

	public ScrollTextPane() {
		this.setViewportView(textPane);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textPane.setEditable(false);
	}

	public void clearAll() {
		textPane.setText(null);
	}

	public void setText(String text) {
		textPane.setText(text);
		textPane.setCaretPosition(0); // Scroll to the top after adding components
	}

	/**
	 * @return JTextPane return the textPane
	 */
	public JTextPane getTextPane() {
		return textPane;
	}

	/**
	 * @param textPane the textPane to set
	 */
	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}
}
