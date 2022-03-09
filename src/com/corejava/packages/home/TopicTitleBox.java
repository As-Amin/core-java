package com.corejava.packages.home;

import java.awt.Font;

import javax.swing.JTextField;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

public class TopicTitleBox {
	private JTextField topicNameArea = new JTextField();

	public JTextField Generate() {
		topicNameArea = new JTextField();
		topicNameArea.setBorder(null);
		topicNameArea.setBackground(null);
		topicNameArea.setForeground(Colors.THEME.getColor());
		topicNameArea.setFont(new Font(FN.CONSOLAS.getFN(), Font.BOLD, FS.TOPIC_TITLE.getFS()));
		topicNameArea.setEditable(false);
		return topicNameArea;
	}

	public void SetTitleBox(String noteName) {
		ClearAll();
		topicNameArea.setText(noteName);
	}

	public void ClearAll() {
		topicNameArea.setText(null);
	}

}
