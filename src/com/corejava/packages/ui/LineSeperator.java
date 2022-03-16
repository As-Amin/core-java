package com.corejava.packages.ui;

import javax.swing.JSeparator;

public class LineSeperator extends JSeparator {
	private int orientation;

	public LineSeperator(int orientation) {
		this.orientation = orientation;
	}

	public JSeparator Generate() {
		this.setOrientation(orientation);
		return this;
	}

	/**
	 * @return int return the orientation
	 */
	public int getOrientation() {
		return orientation;
	}
}
