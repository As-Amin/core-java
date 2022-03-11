package com.corejava.packages.home;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.colors.Colors;
import com.corejava.packages.components.MultipleChoiceQuiz;
import com.corejava.packages.components.TextPaneImage;
import com.corejava.packages.components.TextPaneText;
import com.corejava.packages.components.TextQuiz;
import com.corejava.packages.components.TrueFalseQuiz;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

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
				String subheading = allParagraphs.getJSONObject(i).getString("subheading");
				String paragraphContent = allParagraphs.getJSONObject(i).getString("content");
				// Append subheading
				if (subheading.length() != 0) {
					TextPaneText textPaneSubheading = new TextPaneText(textPane, subheading,
							Colors.PINK.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), false);
					textPaneSubheading.Generate();
				}
				// Append paragraph content
				if (paragraphContent.length() != 0) {
					TextPaneText textPaneParagraph = new TextPaneText(textPane, paragraphContent,
							Colors.WHITE.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), false);
					textPaneParagraph.Generate();
				}
				parseImages(allParagraphs.getJSONObject(i).getJSONArray("images"));
				parseTextQuiz(allParagraphs.getJSONObject(i).getJSONArray("textQuiz"));
				parseTrueFalseQuiz(allParagraphs.getJSONObject(i).getJSONArray("trueFalseQuiz"));
				parseMultipleChoiceQuiz(
						allParagraphs.getJSONObject(i).getJSONArray("multipleChoiceQuiz"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseImages(JSONArray imagesArray) throws BadLocationException, IOException {
		// Convert images array contents to string array lists (image urls and captions)
		ArrayList<String> imagesUrlList = new ArrayList<String>();
		ArrayList<String> captionsList = new ArrayList<String>();
		for (int j = 0; j < imagesArray.length(); j++) {
			imagesUrlList.add(imagesArray.getJSONObject(j).getString("url"));
			captionsList.add(imagesArray.getJSONObject(j).getString("caption"));
		}
		// Append images and captions
		for (int j = 0; j < imagesUrlList.size(); j++) {
			TextPaneImage textPaneImage = new TextPaneImage(imagesUrlList.get(j), textPane);
			textPaneImage.Generate();
			TextPaneText textPaneCaption = new TextPaneText(textPane,
					("Caption: " + captionsList.get(j)), Colors.YELLOW.getColor(),
					FS.TOPIC_TEXT.getFS(), FN.CONSOLAS.getFN(), false);
			textPaneCaption.Generate();
		}
	}

	private void parseTextQuiz(JSONArray textQuizArray) throws BadLocationException, IOException {
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		for (int j = 0; j < textQuizArray.length(); j++) {
			questionList.add(textQuizArray.getJSONObject(j).getString("question"));
			answerList.add(textQuizArray.getJSONObject(j).getString("answer"));
		}
		// Append text quiz question and text quiz
		for (int j = 0; j < questionList.size(); j++) {
			TextPaneText textPaneQuestion = new TextPaneText(textPane, questionList.get(j),
					Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), true);
			textPaneQuestion.Generate();
			TextQuiz textQuiz = new TextQuiz(answerList.get(j), textPane);
			textQuiz.Generate();
		}
	}

	private void parseTrueFalseQuiz(JSONArray trueFalseQuizArray)
			throws BadLocationException, IOException {
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		for (int j = 0; j < trueFalseQuizArray.length(); j++) {
			questionList.add(trueFalseQuizArray.getJSONObject(j).getString("question"));
			answerList.add(trueFalseQuizArray.getJSONObject(j).getString("answer"));
		}
		// Append text quiz question and text quiz
		for (int j = 0; j < questionList.size(); j++) {
			TextPaneText textPaneQuestion = new TextPaneText(textPane, questionList.get(j),
					Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), true);
			textPaneQuestion.Generate();
			TrueFalseQuiz trueFalseQuiz = new TrueFalseQuiz(answerList.get(j), textPane);
			trueFalseQuiz.Generate();
		}
	}

	private void parseMultipleChoiceQuiz(JSONArray multipleChoiceQuizArray)
			throws BadLocationException, IOException {
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> optionsList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		for (int j = 0; j < multipleChoiceQuizArray.length(); j++) {
			questionList.add(multipleChoiceQuizArray.getJSONObject(j).getString("question"));
			answerList.add(multipleChoiceQuizArray.getJSONObject(j).getString("answer"));
			JSONArray optionsArray =
					multipleChoiceQuizArray.getJSONObject(j).getJSONArray("options");
			for (int k = 0; k < optionsArray.length(); k++) {
				optionsList.add(optionsArray.getJSONObject(k).getString("option"));
			}
		}
		// Append text quiz question and text quiz
		for (int j = 0; j < questionList.size(); j++) {
			TextPaneText textPaneQuestion = new TextPaneText(textPane, questionList.get(j),
					Colors.YELLOW.getColor(), FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), true);
			textPaneQuestion.Generate();
			MultipleChoiceQuiz multipleChoiceQuiz =
					new MultipleChoiceQuiz(optionsList, answerList.get(j), textPane);
			multipleChoiceQuiz.Generate();
		}
	}

	public void ClearAll() {
		textPane.setText(null);
	}
}
