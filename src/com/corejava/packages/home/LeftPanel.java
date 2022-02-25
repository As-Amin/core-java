package com.corejava.packages.home;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.colors.DarkModeColors;

public class LeftPanel {

	private JPanel leftPanel = new JPanel();
	private JScrollPane leftPanelScrollPane;
	private JLabel logoLabel;
	private ArrayList<JButton> allTopicButtons = new ArrayList<JButton>();
	
	public JPanel GetLeftPanel() {
		return leftPanel;
	}

	//Generates the scroll panel for which the topics will be displayed,
	//loads the topics onto the scrollpanel
	public JScrollPane GenerateTopicScroll() {
		leftPanelScrollPane = new JScrollPane(leftPanel);
		leftPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		leftPanelScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		this.generateTopicButtons();
		return leftPanelScrollPane;
	}

	public JLabel GenerateLogo() {
		logoLabel = new JLabel(Main.getAppName());
		logoLabel.setForeground(DarkModeColors.DARK3_THEME_COLOR.getColor());
		logoLabel.setFont(new Font(FN.LOGO.getFN(), Font.BOLD, FS.SIDE_LOGO.getFS()));
		return logoLabel;
	}

	private void generateTopicButtons() {
		File directory = new File(Main.getDirectory());
		File[] listOfAllTopics = directory.listFiles(); // Get array of all topic files

		for (File topic : listOfAllTopics) {
			if (topic.isFile() && topic.exists()) {
				String arr[] = topic.getName().toString().split("\\.", 2);
				String topicName = arr[0]; // Title of the topic

				if (topicName.length() > 0) {
					JButton topicNameButton = new JButton(" " + topicName);
					leftPanel.add(configureButton(topicNameButton),  "gapy 10");
					allTopicButtons.add(topicNameButton);

					// Add an action listener to all topics buttons
					topicNameButton.addActionListener(
							new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									for (JButton button : allTopicButtons) {
										button.setForeground(null);
										button.setBorder(null);
									}
									topicNameButton.setForeground(DarkModeColors.DARK3_THEME_COLOR.getColor());
									topicNameButton.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, DarkModeColors.DARK3_THEME_COLOR.getColor()));
									//setWorkingAreaToVisible(); // Make working area visible
									//notepad.OpenFile(note, noteName);
								}
							});
				}
			}
		}
	}

	private JButton configureButton(JButton button) {
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(null);
		button.setBackground(null);
		button.setFont(new Font(FN.MAIN.getFN(), Font.BOLD, FS.SIDE_TEXT.getFS()));
		return(button);
	}

}
