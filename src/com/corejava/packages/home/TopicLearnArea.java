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
						appendTextQuiz(answer);
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
						appendTrueOrFalseQuiz(answer);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void appendText(String str, Color color, int fontSize, String fontName,
			Boolean isQuestion) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();
		Style style = textArea.addStyle("", null);
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
		StyledDocument document = (StyledDocument) textArea.getDocument();
		BufferedImage BI = ImageIO.read(new File(Main.getImagesDirectory() + url));
		ImageIcon image = new ImageIcon(BI);
		Style style = document.addStyle("", null);
		StyleConstants.setIcon(style, image);
		document.insertString(document.getLength(), "String", style);
		document.insertString(document.getLength(), "\n\n", null);
	}

	private void appendTextQuiz(String answer) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();

		JPanel panel = new JPanel();
		panel.setBackground(null);
		panel.setLayout(new MigLayout());

		JTextField answerField = new JTextField();
		answerField.putClientProperty("JTextField.placeholderText", "Your answer here...");
		answerField.setBackground(Colors.BACKGROUND.getColor());
		answerField.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		panel.add(answerField, "wmin 70%, grow");

		JButton submit = new JButton("Submit");
		submit.setForeground(Colors.WHITE.getColor());
		submit.setBackground(Colors.THEME.getColor());
		submit.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		panel.add(submit, "shrink");

		textArea.insertComponent(panel);
		document.insertString(document.getLength(), "\n\n", null);

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// If the answer contains the text that is in the answer string from the JSON
				// file, it is correct.
				if (answerField.getText().equalsIgnoreCase(answer)) {
					System.out.println("Correct");
				} else {
					System.out.println("Hey wrong answer");
				}
			}
		});
	}

	private void appendTrueOrFalseQuiz(String answer) throws BadLocationException {
		StyledDocument document = (StyledDocument) textArea.getDocument();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(null);
		buttonPanel.setLayout(new MigLayout());

		JButton trueButton = new JButton("True");
		trueButton.setForeground(Colors.WHITE.getColor());
		trueButton.setBackground(Colors.THEME.getColor());
		trueButton.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		buttonPanel.add(trueButton);

		JButton falseButton = new JButton("False");
		falseButton.setForeground(Colors.WHITE.getColor());
		falseButton.setBackground(Colors.THEME.getColor());
		falseButton.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
		buttonPanel.add(falseButton);

		textArea.insertComponent(buttonPanel);
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
