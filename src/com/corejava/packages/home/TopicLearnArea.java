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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
	private JSONObject jsonObject;

	public JScrollPane Generate() {
		textArea = new JTextPane();
		scrollArea = new JScrollPane(textArea);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textArea.setBackground(Colors.BACKGROUND_SECONDARY.getColor());
		textArea.setContentType("text/html");
		textArea.setEditable(false);
		return scrollArea;
	}

	public void OpenFile(String fileName, String fileType) throws IOException {
		ClearAll();
		generateJSONObject(fileName, fileType);
		parseJsonFile();
		textArea.setCaretPosition(0); // Scroll to the top after adding components
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
				appendSubheading(subheading);
				appendParagraphContent(paragraphContent);
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
			for (int i = 0; i < allImages.length(); i++) {
				int number = Integer.parseInt(allImages.getJSONObject(i).getString("number"));
				if (number == paragraphNumber) {
					String url = allImages.getJSONObject(i).getString("url");
					String caption = allImages.getJSONObject(i).getString("caption");
					appendImage(url);
					appendImageCaption(caption);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseJsonTextQuiz(int paragraphNumber) throws IOException {
		try {
			JSONArray allTextQuizzes = jsonObject.getJSONArray("textQuiz");
			for (int i = 0; i < allTextQuizzes.length(); i++) {
				int number = Integer.parseInt(allTextQuizzes.getJSONObject(i).getString("number"));
				if (number == paragraphNumber) {
					String question = allTextQuizzes.getJSONObject(i).getString("question");
					String answer = allTextQuizzes.getJSONObject(i).getString("answer");
					appendQuestion(question);
					appendTextQuiz(answer);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseJsonTrueOrFalseQuiz(int paragraphNumber) throws IOException {
		try {
			JSONArray allTrueOrFalseQuizzes = jsonObject.getJSONArray("trueOrFalseQuiz");
			for (int i = 0; i < allTrueOrFalseQuizzes.length(); i++) {
				int number = Integer
						.parseInt(allTrueOrFalseQuizzes.getJSONObject(i).getString("number"));
				if (number == paragraphNumber) {
					String question = allTrueOrFalseQuizzes.getJSONObject(i).getString("question");
					String answer = allTrueOrFalseQuizzes.getJSONObject(i).getString("answer");
					appendQuestion(question);
					appendTrueOrFalseQuiz(answer);
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
		StyleConstants.setForeground(style, Colors.PINK.getColor());
		document.insertString(document.getLength(), str, style);
		document.insertString(document.getLength(), "\n\n", null);
	}

	private void appendParagraphContent(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
		StyleConstants.setFontFamily(style, FN.NOTO.getFN());
		StyleConstants.setFontSize(style, FS.TOPIC_TEXT.getFS());
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Colors.WHITE.getColor());
		document.insertString(document.getLength(), str, style);
		document.insertString(document.getLength(), "\n\n", null);
	}

	private void appendImageCaption(String str) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
		StyleConstants.setFontFamily(style, FN.CONSOLAS.getFN());
		StyleConstants.setFontSize(style, FS.TOPIC_TEXT.getFS());
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Colors.YELLOW.getColor());
		document.insertString(document.getLength(), str, style);
		document.insertString(document.getLength(), "\n\n\n", null);
	}

	private void appendImage(String url) throws BadLocationException, IOException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		BufferedImage BI = ImageIO.read(new File(Main.getImagesDirectory() + url));
		ImageIcon image = new ImageIcon(BI);
		Style style = document.addStyle("", null);
		StyleConstants.setIcon(style, image);
		document.insertString(document.getLength(), "String", style);
		document.insertString(document.getLength(), "\n\n", null);
	}

	private void appendQuestion(String question) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
		StyleConstants.setFontFamily(style, FN.NOTO.getFN());
		StyleConstants.setFontSize(style, FS.TOPIC_TEXT.getFS());
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Colors.YELLOW.getColor());
		document.insertString(document.getLength(), question, style);
		document.insertString(document.getLength(), "\n\n", null);
	}

	private void appendTextQuiz(String answer) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		JTextField answerArea = new JTextField();
		answerArea.setBackground(Colors.BACKGROUND.getColor());
		answerArea.setForeground(Colors.WHITE.getColor());
		answerArea.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		textArea.insertComponent(answerArea);
		document.insertString(document.getLength(), "\n\n", null);

		JButton submit = new JButton("Submit");
		submit.setBorder(null);
		submit.setForeground(Colors.WHITE.getColor());
		submit.setBackground(Colors.THEME.getColor());
		submit.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Colors.THEME.getColor()));
		submit.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		textArea.insertComponent(submit);
		document.insertString(document.getLength(), "\n\n", null);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// If the answer contains the text that is in the answer string from the JSON
				// file, it is correct.
				if (answerArea.getText().toLowerCase().indexOf(answer.toLowerCase()) != -1) {
					System.out.println("Correct");
				} else {
					System.out.println("Hey wrong answer");
				}
			}
		});
	}

	private void appendTrueOrFalseQuiz(String answer) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		JButton trueButton = new JButton("True");
		trueButton.setBorder(null);
		trueButton.setForeground(Colors.WHITE.getColor());
		trueButton.setBackground(Colors.THEME.getColor());
		trueButton.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Colors.THEME.getColor()));
		trueButton.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		textArea.insertComponent(trueButton);
		document.insertString(document.getLength(), "\n\n", null);

		JButton falseButton = new JButton("False");
		falseButton.setBorder(null);
		falseButton.setForeground(Colors.WHITE.getColor());
		falseButton.setBackground(Colors.THEME.getColor());
		falseButton.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Colors.THEME.getColor()));
		falseButton.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		textArea.insertComponent(falseButton);
		document.insertString(document.getLength(), "\n\n", null);

		trueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// If the answer contains the text that is in the answer string from the JSON
				// file, it is correct.
				if (trueButton.getText().equalsIgnoreCase(answer)) {
					System.out.println("Correct");
				} else {
					System.out.println("Hey wrong answer");
				}
			}
		});

		falseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// If the answer contains the text that is in the answer string from the JSON
				// file, it is correct.
				if (falseButton.getText().equalsIgnoreCase(answer)) {
					System.out.println("Correct");
				} else {
					System.out.println("Hey wrong answer");
				}
			}
		});
	}

	public void ClearAll() {
		textArea.setText(null);
	}
}
