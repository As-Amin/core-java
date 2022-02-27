package com.corejava.packages.home;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

import net.miginfocom.swing.MigLayout;

public class TopicList {
	private JPanel topicListPanel = new JPanel();
	private JScrollPane topicScrollPane;

	private TopicLearnArea topicLearnArea;
	private TopicTitleBox topicTitleBox;

	private ArrayList<JButton> allTopicButtons = new ArrayList<JButton>();

	public TopicList(TopicTitleBox topicTitleBox, TopicLearnArea topicLearnArea) {
		this.topicLearnArea = topicLearnArea; //Used to open file on button click
		this.topicTitleBox = topicTitleBox;
	}

	//Generates the scroll panel for which the topics will be displayed,
	//loads the topics onto the scrollpanel
	public JPanel Generate() {
		topicListPanel.setLayout(new MigLayout("wrap"));
		topicScrollPane = new JScrollPane(topicListPanel);
		topicScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topicScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		this.generateTopicButtons();
		return topicListPanel;
	}

	public void generateTopicButtons() {
		File directory = new File(Main.getDirectory());
		File[] unsortedTopics = directory.listFiles(); // Get array of all topic files
		ArrayList<File> sortedTopics = new ArrayList<File>(); //Modifiable arraylist of files
		for(File topic : unsortedTopics) {
			sortedTopics.add(topic);
		}
		Collections.sort(sortedTopics); //Sort all of the topics by numerical order
		for (File topic : sortedTopics) {
			if (topic.isFile() && topic.exists()) {
				String arr[] = topic.getName().toString().split("\\.", 2);
				String topicName = arr[0]; // Title of the topic

				if (topicName.length() > 0) {
					JButton topicNameButton = new JButton(" " + topicName);
					topicListPanel.add(configureButton(topicNameButton),  "gapy 10, w 90%!");
					allTopicButtons.add(topicNameButton);

					// Add an action listener to all topics buttons
					topicNameButton.addActionListener(
							new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									for (JButton button : allTopicButtons) {
										button.setForeground(null);
										button.setBorder(null);
									}
									topicNameButton.setForeground(Colors.DARK3_THEME_COLOR.getColor());
									topicNameButton.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Colors.DARK3_THEME_COLOR.getColor()));
									topicTitleBox.SetTitleBox(topicName);
									topicLearnArea.OpenFile(topic, topicName);
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
