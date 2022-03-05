package com.corejava.packages.home;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

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

	private TopicLearnArea topicLearnArea;
	private TopicTitleBox topicTitleBox;

	private ArrayList<JButton> allTopicButtons = new ArrayList<JButton>();

	public TopicList(TopicTitleBox topicTitleBox, TopicLearnArea topicLearnArea) {
		this.topicLearnArea = topicLearnArea; // Used to open file on button click
		this.topicTitleBox = topicTitleBox;
	}

	// Generates the scroll panel for which the topics will be displayed,
	// loads the topics onto the scrollpanel
	public JScrollPane Generate() {
		topicListPanel.setLayout(new MigLayout("wrap"));
		// "gapy 10, w 90%!"
		JScrollPane topicScrollPane = new JScrollPane(topicListPanel);
		topicScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topicScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		this.generateTopicButtons();
		return topicScrollPane;
	}

	public void generateTopicButtons() {
		File directory = new File(Main.getDirectory());
		File[] unsortedTopicsArray = directory.listFiles(); // Get array of all topic files
		ArrayList<File> unsortedTopicsList = new ArrayList<File>(); // Modifiable arraylist of files
		for (File topic : unsortedTopicsArray) {
			unsortedTopicsList.add(topic);
		}
		ArrayList<File> sortedTopics = sortTopics(unsortedTopicsList);

		for (File topic : sortedTopics) {
			if (topic.isFile() && topic.exists()) {
				String topicAndFileType[] = topic.getName().split("\\.", 2);
				String numberAndTopic[] = topicAndFileType[0].split("\\)", 2);
				if (numberAndTopic[1].length() > 0) {
					JButton topicNameButton =
							new JButton(" " + numberAndTopic[0] + ")" + numberAndTopic[1]);
					topicListPanel.add(configureButton(topicNameButton), "hmin 30");
					allTopicButtons.add(topicNameButton);
					// Add an action listener to all topics buttons
					topicNameButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for (JButton button : allTopicButtons) {
								button.setForeground(Colors.DARK4_FADEDTEXT_COLOR.getColor());
								button.setBorder(null);
							}
							topicNameButton.setForeground(null);
							topicNameButton.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0,
									Colors.DARK3_THEME_COLOR.getColor()));
							topicTitleBox.SetTitleBox(numberAndTopic[0] + ")" + numberAndTopic[1]);
							topicLearnArea.OpenFile(topicAndFileType[0]);
						}
					});
				}
			}
		}
	}

	private ArrayList<File> sortTopics(ArrayList<File> topics) {
		ArrayList<File> sortedTopics = topics;
		// Bubble sort the topics list by the number before the close bracket
		int n = sortedTopics.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				String topicAndFileType1[] = sortedTopics.get(j).getName().split("\\.", 2);
				String numberAndTopic1[] = topicAndFileType1[0].split("\\)", 2);
				int first = Integer.parseInt(numberAndTopic1[0]);

				String topicAndFileType2[] = sortedTopics.get(j + 1).getName().split("\\.", 2);
				String numberAndTopic2[] = topicAndFileType2[0].split("\\)", 2);
				int second = Integer.parseInt(numberAndTopic2[0]);
				if (first > second) {
					Collections.swap(sortedTopics, j, j + 1);
				}
			}
		}
		return sortedTopics;
	}

	private JButton configureButton(JButton button) {
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(null);
		button.setForeground(Colors.DARK4_FADEDTEXT_COLOR.getColor());
		button.setBackground(null);
		button.setFont(new Font(FN.MAIN.getFN(), Font.BOLD, FS.SIDE_TEXT.getFS()));
		return (button);
	}
}
