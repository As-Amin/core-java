package com.corejava.packages.home;

import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

public class TopicLearnArea {
	private JScrollPane scrollArea;
	private JTextPane textArea;

	public JScrollPane Generate() {
		textArea = new JTextPane();
		scrollArea = new JScrollPane(textArea);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		textArea.setBackground(Colors.DARK2_BACKGROUND_SECONDARY.getColor());
		textArea.setEditable(false);
		return scrollArea;
	}

	public void OpenFile(String fileName, String fileType) {
		ClearAll();
		try {
			FileReader fr =
					new FileReader(new File(Main.getDirectory(), fileName + '.' + fileType));
			textArea.read(fr, "textArea");
			fr.close();
		} catch (IOException IOE) {
			textArea.setText("Topic not found");
		}
	}

	public void ClearAll() {
		textArea.setText(null);
	}
}
