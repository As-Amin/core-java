package com.corejava.packages.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ScrollTextArea extends JTextPane {
	private JScrollPane scrollArea;

	public JScrollPane generate() {
		scrollArea = new JScrollPane(this);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setEditable(false);
		return scrollArea;
	}

	public void clearAll() {
		this.setText(null);
	}

	public void setFeedbackArea(String feedback) {
		clearAll();
		this.setText(feedback);
		this.setCaretPosition(0); // Scroll to the top after adding components
	}

	/**
	 * @return JScrollPane return the scrollArea
	 */
	public JScrollPane getScrollArea() {
		return scrollArea;
	}

	/**
	 * @param scrollArea the scrollArea to set
	 */
	public void setScrollArea(JScrollPane scrollArea) {
		this.scrollArea = scrollArea;
	}

}
