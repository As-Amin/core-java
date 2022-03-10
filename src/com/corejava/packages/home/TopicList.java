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
		this.generateButtonsAndLabels();
		return topicScrollPane;
	}

	public void generateButtonsAndLabels() {
		File directory = new File(Main.getTopicsDirectory());
		File[] unsortedArray = directory.listFiles(); // Get array of all topic files
		ArrayList<File> unsortedList = new ArrayList<File>(); // Modifiable arraylist of files
		for (File file : unsortedArray) {
			unsortedList.add(file);
		}
		ArrayList<File> sortedList = sort(unsortedList);
		for (File file : sortedList) {
			int number = getTopicNumber(file);
			String name = getTopicName(file);
			String fileType = getFileType(file);

			if (fileType.equalsIgnoreCase("section")) {
				// Get rid of the 'a' or 'z' (first character) as this is used only to
				// prioritise the file in the directory order.
				JLabel topicSectionLabel = new JLabel(name.substring(1));
				topicListPanel.add(configureSectionLabel(topicSectionLabel), "hmin 30, grow");
			}
			if (fileType.equalsIgnoreCase("json")) {
				JButton button = new JButton(" " + number + ") " + name);
				topicListPanel.add(configureButton(button), "hmin 30, grow");
				allTopicButtons.add(button);
				// Add an action listener to all topics buttons
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						for (JButton button : allTopicButtons) {
							button.setForeground(Colors.FADED_WHITE.getColor());
							button.setBorder(null);
						}
						button.setForeground(null);
						button.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,
								Colors.THEME.getColor()));
						try {
							HomeScreen.topicTitleBox.SetTitleBox(name);
							HomeScreen.topicLearnArea.OpenFile(file.getName());
						} catch (IOException IOE) {
							IOE.printStackTrace();
						}
					}
				});
			}
		}
	}

	/*
	 * Bubble sort the topics list by the number before the close bracket
	 */
	private ArrayList<File> sort(ArrayList<File> unsortedList) {
		ArrayList<File> sortedList = unsortedList;
		int n = sortedList.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				int topicNumberFirst = getTopicNumber(sortedList.get(j));
				int topicNumberSecond = getTopicNumber(sortedList.get(j + 1));
				if (topicNumberFirst > topicNumberSecond) {
					Collections.swap(sortedList, j, j + 1);
				}
			}
		}
		return sortedList;
	}

	private int getTopicNumber(File file) {
		String numberTopic[] = file.getName().split("\\)", 2);
		return Integer.parseInt(numberTopic[0]);
	}

	private String getTopicName(File file) {
		String topicAndFileType[] = file.getName().split("\\.", 2);
		String numberTopic[] = topicAndFileType[0].split("\\) ", 2);
		return numberTopic[1];
	}

	private String getFileType(File file) {
		String topicFileType[] = file.getName().split("\\.", 2);
		return topicFileType[1];
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
