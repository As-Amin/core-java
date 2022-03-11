package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class HomeScreen extends JFrame {
	private int panelWidth = 750;
	private int panelHeight = 550;
	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();
	// Right panel - public and global because needs to be modified by different
	// classes and functions outside of this one i.e. TopicList
	public static TopicTitleBox topicTitleBox = new TopicTitleBox();
	public static TopicLearnArea topicLearnArea = new TopicLearnArea();
	// Left panel
	private Logo logo = new Logo();
	private TopicList topicList = new TopicList();

	public HomeScreen() {
		contentPane.setLayout(new MigLayout("", "[fill,30%!][fill,grow]", // width, column
				"[fill,grow][]")); // height, row

		// Left panel
		contentPane.add(topicList.Generate(), "cell 0 0");
		contentPane.add(logo.Generate(), "cell 0 1");

		// Right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new MigLayout("", "[fill,grow]", // width, column
				"[fill,10%!][fill,grow]")); // height, row
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
		rightPanel.add(topicLearnArea.Generate(), "cell 0 1");
		contentPane.add(rightPanel, "cell 1 0 2 2");

		setupFrame();
		setInitialTitleBox();
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		frame.pack();
		frame.setLocation(1000, 300);
		frame.setMinimumSize(new Dimension(panelWidth, panelHeight));
		frame.setSize(new Dimension(panelWidth, panelHeight));
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	private void setInitialTitleBox() {
		topicTitleBox.SetTitleBox("Select a topic!");
	}
}
