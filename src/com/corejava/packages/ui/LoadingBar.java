package com.corejava.packages.ui;

import javax.swing.JProgressBar;

public class LoadingBar extends JProgressBar {

	public void load() {
		this.setValue(0);
		try {
			for (int i = 0; i < 101; i++) {
				Thread.sleep(3);
				this.setValue(i);// Setting value of Progress Bar
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
