package com.corejava.packages.home;

import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.json.JSONArray;
import org.json.JSONObject;

import net.miginfocom.swing.MigLayout;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.quiz.TextQuiz;
import com.corejava.packages.quiz.TrueFalseQuiz;

public class TopicLearnArea {
	private JScrollPane scrollArea;
	private JTextPane textPane;
	private JSONObject jsonObject;

	public JScrollPane Generate() {
		textPane = new JTextPane();
		scrollArea = new JScrollPane(textPane);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textPane.setBackground(Colors.BACKGROUND_SECONDARY.getColor());
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		return scrollArea;
	}

	public void OpenFile(String fileName, String fileType) throws IOException {
		ClearAll();
		generateJSONObject(fileName, fileType);
		parseJsonFile();
		textPane.setCaretPosition(0); // Scroll to the top after adding components
	}

	private void generateJSONObject(String fileName, String fileType) throws IOException {
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
		br.close();
		JSONObject obj = new JSONObject(json);
		jsonObject = obj;
	}

	private void parseJsonFile() throws IOException {
		parseJsonParagraph(); // This method calls the function for images, questions etc.
	}

	private void parseJsonParagraph() throws IOException {
		try {
			JSONArray allParagraphs = jsonObject.getJSONArray("paragraphs");
			for (int i = 0; i < allParagraphs.length(); i++) {
				int number = Integer.parseInt(allParagraphs.getJSONObject(i).getString("number"));
				String subheading = allParagraphs.getJSONObject(i).getString("subheading");
				String paragraphContent = allParagraphs.getJSONObject(i).getString("content");
				// Append subheading
				appendText(subheading, Colors.PINK.getColor(), FS.TOPIC_TEXT.getFS(),
						FN.NOTO.getFN(), false);
				// Append paragraph
				appendText(paragraphContent, Colors.WHITE.getColor(), FS.TOPIC_TEXT.getFS(),
						FN.NOTO.getFN(), false);
				parseJsonImages(number); // Display the image with the same number below paragraph
				parseJsonTextQuiz(number);
				parseJsonTrueOrFalseQuiz(number);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseJsonImages(int paragraphNumber) throws IOException {
		try {
			JSONArray allImages = jsonObject.getJSONArray("images");
			if (allImages.length() != 0) {
				for (int i = 0; i < allImages.length(); i++) {
					int number = Integer.parseInt(allImages.getJSONObject(i).getString("number"));
					if (number == paragraphNumber) {
						String url = allImages.getJSONObject(i).getString("url");
						String caption = allImages.getJSONObject(i).getString("caption");
						appendImage(url);
						// Append caption
						appendText(caption, Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(),
								FN.CONSOLAS.getFN(), false);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseJsonTextQuiz(int paragraphNumber) throws IOException {
		try {
			JSONArray allTextQuizzes = jsonObject.getJSONArray("textQuiz");
			if (allTextQuizzes.length() != 0) {
				for (int i = 0; i < allTextQuizzes.length(); i++) {
					int number =
							Integer.parseInt(allTextQuizzes.getJSONObject(i).getString("number"));
					if (number == paragraphNumber) {
						String question = allTextQuizzes.getJSONObject(i).getString("question");
						String answer = allTextQuizzes.getJSONObject(i).getString("answer");
						// Append question
						appendText(question, Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(),
								FN.NOTO.getFN(), true);
						TextQuiz textQuiz = new TextQuiz(answer, textPane);
						textQuiz.Generate();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	private void parseJsonTrueOrFalseQuiz(int paragraphNumber) throws IOException {
		try {
			JSONArray allTrueOrFalseQuizzes = jsonObject.getJSONArray("trueOrFalseQuiz");
			if (allTrueOrFalseQuizzes.length() != 0) {
				for (int i = 0; i < allTrueOrFalseQuizzes.length(); i++) {
					int number = Integer
							.parseInt(allTrueOrFalseQuizzes.getJSONObject(i).getString("number"));
					if (number == paragraphNumber) {
						String question =
								allTrueOrFalseQuizzes.getJSONObject(i).getString("question");
						String answer = allTrueOrFalseQuizzes.getJSONObject(i).getString("answer");
						appendText(question, Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(),
								FN.NOTO.getFN(), true);
						TrueFalseQuiz trueFalseQuiz = new TrueFalseQuiz(answer, textPane);
						trueFalseQuiz.Generate();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void appendText(String str, Color color, int fontSize, String fontName,
			Boolean isQuestion) throws BadLocationException {
		StyledDocument document = (StyledDocument) textPane.getDocument();
		Style style = textPane.addStyle("", null);
		StyleConstants.setFontFamily(style, fontName);
		StyleConstants.setFontSize(style, fontSize);
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, color);
		document.insertString(document.getLength(), str, style);
		if (isQuestion == true) {
			document.insertString(document.getLength(), "\n", null);
		} else {
			document.insertString(document.getLength(), "\n\n", null);
		}
	}

	private void appendImage(String url) throws BadLocationException, IOException {
		StyledDocument document = (StyledDocument) textPane.getDocument();
		BufferedImage BI = ImageIO.read(new File(Main.getImagesDirectory() + url));
		ImageIcon image = new ImageIcon(BI);
		Style style = document.addStyle("", null);
		StyleConstants.setIcon(style, image);
		document.insertString(document.getLength(), "String", style);
		document.insertString(document.getLength(), "\n\n", null);
	}

	public void ClearAll() {
		textPane.setText(null);
	}
}
