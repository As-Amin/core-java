package com.corejava.packages.home;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
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
		textArea.setBackground(Colors.DARK2_BACKGROUND_SECONDARY.getColor());
		textArea.setContentType("text/html");
		textArea.setEditable(false);
		return scrollArea;
	}

	public void OpenFile(String fileName, String fileType) throws IOException {
		ClearAll();
		this.fileName = fileName;
		this.fileType = fileType;
		parseJsonParagraph();
	}

	private void parseJsonParagraph() throws IOException {
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(
					Paths.get(Main.getTopicsDirectory(), fileName + '.' + fileType));
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
				appendSubheading(subheading);
				appendSubheading("\n\n");
				appendContent(paragraphContent);
				appendContent("\n\n");
				parseJsonImages(number); // Display the image with the same number below paragraph
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseJsonImages(int paragraphNumber) throws IOException {
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(
					Paths.get(Main.getTopicsDirectory(), fileName + '.' + fileType));
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
			JSONArray allImages = obj.getJSONArray("images");
			for (int i = 0; i < allImages.length(); i++) {
				int number = Integer.parseInt(allImages.getJSONObject(i).getString("number"));
				if (number == paragraphNumber) {
					String url = allImages.getJSONObject(i).getString("url");
					String caption = allImages.getJSONObject(i).getString("caption");
					appendImage(url);
					appendCaption("\n\n");
					appendCaption(caption);
					appendCaption("\n\n");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void appendSubheading(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
		StyleConstants.setFontFamily(style, FN.NOTO.getFN());
		StyleConstants.setFontSize(style, FS.TOPIC_TEXT.getFS());
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Colors.DARK4_THEME_SECONDARY_COLOR.getColor());
		document.insertString(document.getLength(), str, style);
	}

	private void appendContent(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
		StyleConstants.setFontFamily(style, FN.NOTO.getFN());
		StyleConstants.setFontSize(style, FS.TOPIC_TEXT.getFS());
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Colors.DARK6_TEXT_COLOR.getColor());
		document.insertString(document.getLength(), str, style);
	}

	private void appendCaption(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
		StyleConstants.setFontFamily(style, FN.CONSOLAS.getFN());
		StyleConstants.setFontSize(style, FS.TOPIC_TEXT.getFS());
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Colors.DARK3_THEME_COLOR.getColor());
		document.insertString(document.getLength(), str, style);
	}

	private void appendImage(String url) throws BadLocationException, IOException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		BufferedImage BI = ImageIO.read(new File(Main.getImagesDirectory() + url));
		ImageIcon image = new ImageIcon(BI);
		Style style = document.addStyle("", null);
		StyleConstants.setIcon(style, image);
		document.insertString(document.getLength(), "String", style);
	}

	public void ClearAll() {
		textArea.setText(null);
	}
}
