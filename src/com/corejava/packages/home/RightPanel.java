package com.corejava.packages.home;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.colors.DarkModeColors;

public class RightPanel {
	private JPanel rightPanel = new JPanel();
	private JTextField topicNameArea = new JTextField();
	private JScrollPane scrollTextArea;
	private JTextPane textArea;

	public JPanel GetRightPanel() {
		return rightPanel;
	}

	public JScrollPane GenerateTextArea() {
		textArea = new JTextPane();
		scrollTextArea = new JScrollPane(textArea);
		scrollTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setFont(new Font(FN.MAIN.getFN(), Font.PLAIN, FS.NOTE_TEXT.getFS()));
		textArea.setBackground(DarkModeColors.DARK2_BACKGROUND_SECONDARY.getColor());
		return scrollTextArea;
	}

	public JTextField GenerateTitleBox() {
		topicNameArea = new JTextField();
		topicNameArea.setBackground(null);
		topicNameArea.setForeground(DarkModeColors.DARK3_THEME_COLOR.getColor());
		topicNameArea.setFont(new Font(FN.MAIN.getFN(), Font.BOLD, FS.NOTE_TITLE.getFS()));
		return topicNameArea;
	}

}
