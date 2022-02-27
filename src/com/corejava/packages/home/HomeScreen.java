package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.corejava.packages.colors.Colors;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class HomeScreen extends JFrame{
	private int panelWidth = 700;
	private int panelHeight = 500;

	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();
	
	//Right panel
	TopicTitleBox topicTitleBox = new TopicTitleBox();
	TopicLearnArea topicLearnArea = new TopicLearnArea();
	
	//Left panel
	Logo logo = new Logo();
	TopicList topicList = new TopicList(topicTitleBox, topicLearnArea);
	
	public HomeScreen() {
		contentPane.setLayout(
				new MigLayout(
						"",
						"[fill,30%!][fill,grow]", // width, column
						"[fill,grow][]")); // height, row
		
		//Left panel
		contentPane.add(topicList.Generate(), "cell 0 0");
		contentPane.add(logo.Generate(), "cell 0 1");
		
		//Right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new MigLayout(
				"", 
				"[fill,grow]", // width, column
				"[fill,10%!][fill,grow]")); // height, row
		//rightPanel.setBackground(Colors.DARK2_BACKGROUND_SECONDARY.getColor());
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
	    rightPanel.add(topicLearnArea.Generate(), "cell 0 1");
		contentPane.add(rightPanel, "cell 1 0 2 2");
		
		setupFrame();
	}
	
	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		frame.pack();
		frame.setLocation(1000, 300);
		frame.setMinimumSize(new Dimension(panelWidth, panelHeight));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}
}
