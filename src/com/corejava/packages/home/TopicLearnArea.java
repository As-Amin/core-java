package com.corejava.packages.home;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.learn_content.TextPaneImage;
import com.corejava.packages.learn_content.TextPaneText;
import com.corejava.packages.learn_content.MultipleChoiceQuiz;
import com.corejava.packages.learn_content.TextQuiz;
import com.corejava.packages.learn_content.TrueFalseQuiz;

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

	public void OpenFile(String fileName) throws IOException {
		ClearAll();
		generateJSONObject(fileName);
		parseJsonFile();
		textPane.setCaretPosition(0); // Scroll to the top after adding components
	}

	private void generateJSONObject(String fileName) throws IOException {
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(Paths.get(Main.getTopicsDirectory(), fileName));
		} catch (NullPointerException NPE) {
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
		try {
			JSONArray allParagraphs = jsonObject.getJSONArray("paragraphs");
			for (int i = 0; i < allParagraphs.length(); i++) {
				int number = Integer.parseInt(allParagraphs.getJSONObject(i).getString("number"));
				String subheading = allParagraphs.getJSONObject(i).getString("subheading");
				String paragraphContent = allParagraphs.getJSONObject(i).getString("content");
				// Append subheading
				if (subheading.length() != 0) {
					TextPaneText textPaneText = new TextPaneText(textPane, subheading,
							Colors.PINK.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), false);
					textPaneText.Generate();
				}
				// Append paragraph
				if (paragraphContent.length() != 0) {
					TextPaneText textPaneText = new TextPaneText(textPane, paragraphContent,
							Colors.WHITE.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), false);
					textPaneText.Generate();
				}
				parseJsonImages(number); // Display the image with the same number below paragraph
				parseJsonTextQuiz(number);
				parseJsonTrueOrFalseQuiz(number);
				parseMultipleChoiceQuiz(number);
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
						TextPaneImage image = new TextPaneImage(url, textPane);
						image.Generate();
						// Append caption
						if (caption.length() != 0) {
							TextPaneText textPaneText = new TextPaneText(textPane,
									("Figure " + (i + 1) + ": " + caption),
									Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(),
									FN.CONSOLAS.getFN(), false);
							textPaneText.Generate();
						}
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
						if (question.length() != 0) {
							TextPaneText textPaneText =
									new TextPaneText(textPane, question, Colors.YELLOW.getColor(),
											FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), true);
							textPaneText.Generate();
							TextQuiz textQuiz = new TextQuiz(answer, textPane);
							textQuiz.Generate();
						}
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
						if (question.length() != 0) {
							TextPaneText textPaneText =
									new TextPaneText(textPane, question, Colors.YELLOW.getColor(),
											FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), true);
							textPaneText.Generate();
							TrueFalseQuiz trueFalseQuiz = new TrueFalseQuiz(answer, textPane);
							trueFalseQuiz.Generate();
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseMultipleChoiceQuiz(int paragraphNumber) throws IOException {
		try {
			JSONArray allMultipleChoiceQuizzes = jsonObject.getJSONArray("multipleChoiceQuiz");
			if (allMultipleChoiceQuizzes.length() != 0) {
				for (int i = 0; i < allMultipleChoiceQuizzes.length(); i++) {
					int number = Integer.parseInt(
							allMultipleChoiceQuizzes.getJSONObject(i).getString("number"));
					if (number == paragraphNumber) {
						String question =
								allMultipleChoiceQuizzes.getJSONObject(i).getString("question");
						JSONArray optionsArray =
								allMultipleChoiceQuizzes.getJSONObject(i).getJSONArray("options");
						ArrayList<String> optionsList = new ArrayList<String>();
						String answer =
								allMultipleChoiceQuizzes.getJSONObject(i).getString("answer");
						for (int j = 0; j < optionsArray.length(); j++) {
							optionsList.add(optionsArray.getJSONObject(j).getString("option"));
						}
						if (question.length() != 0) {
							TextPaneText textPaneText =
									new TextPaneText(textPane, question, Colors.YELLOW.getColor(),
											FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), true);
							textPaneText.Generate();
							MultipleChoiceQuiz multipleChoiceQuiz =
									new MultipleChoiceQuiz(optionsList, answer, textPane);
							multipleChoiceQuiz.Generate();
						}

					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ClearAll() {
		textPane.setText(null);
	}
}
