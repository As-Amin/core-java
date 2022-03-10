package com.corejava.packages.home;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

import net.miginfocom.swing.MigLayout;

public class TopicList {
	private JPanel topicListPanel = new JPanel();
	private ArrayList<JButton> allTopicButtons = new ArrayList<JButton>();

	// Generates the scroll panel for which the topics will be displayed,
	// loads the topics onto the scrollpanel
	public JScrollPane Generate() {
		topicListPanel.setLayout(new MigLayout("wrap"));
		JScrollPane topicScrollPane = new JScrollPane(topicListPanel);
		topicScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topicScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		this.generateTopicButtons();
		return topicScrollPane;
	}

	public void generateTopicButtons() {
		File directory = new File(Main.getTopicsDirectory());
		File[] unsortedTopicsArray = directory.listFiles(); // Get array of all topic files
		ArrayList<File> unsortedTopicsList = new ArrayList<File>(); // Modifiable arraylist of files
		for (File topic : unsortedTopicsArray) {
			unsortedTopicsList.add(topic);
		}
		ArrayList<File> sortedTopics = sort(unsortedTopicsList);
		for (File topicFile : sortedTopics) {
			if (topicFile.isFile() && topicFile.exists()) {
				String topicAndFileType[] = topicFile.getName().split("\\.", 2);
				String numberAndTopic[] = topicAndFileType[0].split("\\)", 2);
				String topicNumber = numberAndTopic[0];
				String topicName = numberAndTopic[1].substring(1);
				String fileType = topicAndFileType[1];
				if (fileType.equalsIgnoreCase("section")) {
					// Get rid of the 'a' or 'z' (first character) as this is used only to
					// prioritise the file in the directory order.
					JLabel topicSectionLabel = new JLabel("" + topicName.substring(1));
					topicListPanel.add(configureSectionLabel(topicSectionLabel), "hmin 30, grow");
				}
				if (fileType.equalsIgnoreCase("json")) {
					JButton topicNameButton = new JButton(" " + topicNumber + ") " + topicName);
					topicListPanel.add(configureButton(topicNameButton), "hmin 30, grow");
					allTopicButtons.add(topicNameButton);
					// Add an action listener to all topics buttons
					topicNameButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							for (JButton button : allTopicButtons) {
								button.setForeground(Colors.FADED_WHITE.getColor());
								button.setBorder(null);
							}
							topicNameButton.setForeground(null);
							topicNameButton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,
									Colors.THEME.getColor()));
							try {
								HomeScreen.topicTitleBox.SetTitleBox(topicName);
								HomeScreen.topicLearnArea.OpenFile((topicNumber + ") " + topicName),
										fileType);
							} catch (IOException IOE) {
								IOE.printStackTrace();
							}
						}
					});
				}
			}
		}
	}

	private ArrayList<File> sort(ArrayList<File> topics) {
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
		button.setForeground(Colors.FADED_WHITE.getColor());
		button.setBackground(null);
		button.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.SIDE_TEXT.getFS()));
		return (button);
	}

	private JLabel configureSectionLabel(JLabel label) {
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Colors.PINK.getColor());
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Colors.PINK.getColor()));
		label.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.SIDE_HEADING.getFS()));
		return (label);
	}
}
