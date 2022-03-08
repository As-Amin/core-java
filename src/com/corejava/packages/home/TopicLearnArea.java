package com.corejava.packages.home;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

public class TopicLearnArea {
	private JScrollPane scrollArea;
	private JTextPane textArea;
	private String fileName;
	private String fileType;

	public JScrollPane Generate() {
		textArea = new JTextPane();
		scrollArea = new JScrollPane(textArea);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		textArea.setBackground(Colors.DARK2_BACKGROUND_SECONDARY.getColor());
		textArea.setEditable(false);
		return scrollArea;
	}

	public void OpenFile(String fileName, String fileType) throws IOException {
		ClearAll();
		// try {
		this.fileName = fileName;
		this.fileType = fileType;
		parseJsonParagraph();
		// FileReader fr =
		// new FileReader(new File(Main.getDirectory(), fileName + '.' + fileType));
		// textArea.read(fr, "textArea");
		// fr.close();
		// } catch (IOException IOE) {
		// textArea.setText("Topic not found");
		// }
	}

	private void parseJsonParagraph() throws IOException {
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(Paths.get(Main.getDirectory(), fileName + '.' + fileType));
		} catch (NullPointerException NPE) {
			// Null pointer exception
			throw new NullPointerException();
		}
		String json = "";
		String line = "";
		while ((line = br.readLine()) != null) {
			json = json + line;
		}
		JSONObject obj = new JSONObject(json);

		try {
			JSONArray allParagraphs = obj.getJSONArray("paragraphs");
			for (int i = 0; i < allParagraphs.length(); i++) {
				int number = Integer.parseInt(allParagraphs.getJSONObject(i).getString("number"));
				String subheading = allParagraphs.getJSONObject(i).getString("subheading");
				String paragraphContent = allParagraphs.getJSONObject(i).getString("content");
				appendStrToLearnArea(subheading);
				appendStrToLearnArea("\n\n");
				appendStrToLearnArea(paragraphContent);
				System.out.println(subheading + paragraphContent);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void appendStrToLearnArea(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		document.insertString(document.getLength(), str, null);
	}


	public void ClearAll() {
		textArea.setText(null);
	}
}
