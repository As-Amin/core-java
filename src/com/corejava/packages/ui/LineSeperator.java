package com.corejava.packages.ui;

import javax.swing.JSeparator;

public class LineSeperator {
	private JSeparator seperator = new JSeparator();
	private int orientation;

	public LineSeperator(int orientation) {
		this.orientation = orientation;
	}

	public JSeparator Generate() {
		seperator.setOrientation(orientation);
		return seperator;
	}

	/**
	 * @return JSeparator return the seperator
	 */
	public JSeparator getSeperator() {
		return seperator;
	}

	/**
	 * @param seperator the seperator to set
	 */
	public void setSeperator(JSeparator seperator) {
		this.seperator = seperator;
	}
}
