package com.corejava.packages.components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.home.Home;
import com.corejava.packages.home.Main;
import org.apache.commons.io.comparator.NameFileComparator;
import net.miginfocom.swing.MigLayout;

public class TopicList {
	private JPanel topicListPanel = new JPanel();
	private ArrayList<JButton> allTopicButtons = new ArrayList<JButton>();
	private final char fileNumberSeperator = ')';

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

	private void generateButtonsAndLabels() {
		File[] files = new File(Main.getTopicsDirectory()).listFiles();
		Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
		for (File section : files) {
			String sectionName = getTopicName(section);
			JLabel sectionLabel = new JLabel(sectionName);
			topicListPanel.add(configureLabel(sectionLabel), "hmin 30, grow");
			for (File topic : section.listFiles()) {
				String topicName = getTopicName(topic);
				JButton topicButton = new JButton(" " + topicName);
				topicListPanel.add(configureButton(topicButton), "hmin 30, grow");
				allTopicButtons.add(topicButton);
				topicButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						try {
							for (JButton button : allTopicButtons) {
								button.setForeground(Colors.FADED_WHITE.getColor());
								button.setBorder(null);
							}
							topicButton.setForeground(null);
							topicButton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,
									Colors.THEME.getColor()));
							Home.topicTitleBox.SetTitleBox(topicName);
							Home.topicLearnArea.OpenFile(topic);
						} catch (IOException IOE) {
							IOE.printStackTrace();
						}
					}
				});
			}
		}
	}

	private String getTopicName(File file) {
		String topicAndFileType[] = file.getName().split("\\.", 2);
		String numberTopic[] = topicAndFileType[0].split("\\" + fileNumberSeperator + " ", 2);
		return numberTopic[1];
	}

	private JButton configureButton(JButton button) {
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(null);
		button.setForeground(Colors.FADED_WHITE.getColor());
		button.setBackground(null);
		button.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.SIDE_TEXT.getFS()));
		return (button);
	}

	private JLabel configureLabel(JLabel label) {
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Colors.THEME_SECONDARY.getColor());
		label.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.SIDE_HEADING.getFS()));
		return (label);
	}
}
