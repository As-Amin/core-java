package com.corejava.packages.swing_components;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.textpane_components.CaptionText;
import com.corejava.packages.textpane_components.Image;
import com.corejava.packages.textpane_components.MultipleChoice;
import com.corejava.packages.textpane_components.QuestionText;
import com.corejava.packages.textpane_components.SubheadingText;
import com.corejava.packages.textpane_components.Text;
import com.corejava.packages.textpane_components.OpenChoice;
import com.corejava.packages.textpane_components.TrueFalse;

public class LearnArea {
	private JScrollPane scrollArea;
	private JTextPane textPane;
	private JSONObject jsonObject;

	public JScrollPane Generate() {
		textPane = new JTextPane();
		scrollArea = new JScrollPane(textPane);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollArea.getVerticalScrollBar().setUnitIncrement(10);
		textPane.setEditable(false);
		return scrollArea;
	}

	public void OpenFile(File topicFile) throws IOException {
		ClearAll();
		generateJSONObject(topicFile);
		parseJsonFile();
		textPane.setCaretPosition(0); // Scroll to the top after adding components
	}

	private void generateJSONObject(File topicFile) throws IOException {
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(topicFile.toPath());
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
					SubheadingText textPaneSubheading = new SubheadingText(textPane, subheading);
					textPaneSubheading.Generate();
				}
				// Append paragraph content
				if (paragraphContent.length() != 0) {
					Text textPaneParagraph = new Text(textPane, paragraphContent);
					textPaneParagraph.Generate();
				}
				parseImages(allParagraphs.getJSONObject(i).getJSONArray("images"));
				parseTextQuestion(allParagraphs.getJSONObject(i).getJSONArray("openChoiceQuiz"));
				parseTrueFalse(allParagraphs.getJSONObject(i).getJSONArray("trueFalseQuiz"));
				parseMultipleChoice(
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
			Image textPaneImage = new Image(imagesUrlList.get(j), textPane);
			textPaneImage.Generate();
			CaptionText textPaneCaption =
					new CaptionText(textPane, ("Caption: " + captionsList.get(j)));
			textPaneCaption.Generate();
		}
	}

	private void parseTextQuestion(JSONArray textQuizArray)
			throws BadLocationException, IOException {
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		for (int j = 0; j < textQuizArray.length(); j++) {
			questionList.add(textQuizArray.getJSONObject(j).getString("question"));
			answerList.add(textQuizArray.getJSONObject(j).getString("answer"));
		}
		// Append text quiz question and text quiz
		for (int j = 0; j < questionList.size(); j++) {
			QuestionText textPaneQuestion = new QuestionText(textPane, questionList.get(j));
			textPaneQuestion.Generate();
			OpenChoice textQuiz = new OpenChoice(answerList.get(j), textPane);
			textQuiz.Generate();
		}
	}

	private void parseTrueFalse(JSONArray trueFalseQuizArray)
			throws BadLocationException, IOException {
		ArrayList<String> questionList = new ArrayList<String>();
		ArrayList<String> answerList = new ArrayList<String>();
		for (int j = 0; j < trueFalseQuizArray.length(); j++) {
			questionList.add(trueFalseQuizArray.getJSONObject(j).getString("question"));
			answerList.add(trueFalseQuizArray.getJSONObject(j).getString("answer"));
		}
		// Append text quiz question and text quiz
		for (int j = 0; j < questionList.size(); j++) {
			QuestionText textPaneQuestion = new QuestionText(textPane, questionList.get(j));
			textPaneQuestion.Generate();
			TrueFalse trueFalseQuiz = new TrueFalse(answerList.get(j), textPane);
			trueFalseQuiz.Generate();
		}
	}

	private void parseMultipleChoice(JSONArray multipleChoiceQuizArray)
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
			QuestionText textPaneQuestion = new QuestionText(textPane, questionList.get(j));
			textPaneQuestion.Generate();
			MultipleChoice multipleChoiceQuiz =
					new MultipleChoice(optionsList, answerList.get(j), textPane);
			multipleChoiceQuiz.Generate();
		}
	}

	public void ClearAll() {
		textPane.setText(null);
	}

	/**
	 * @return JScrollPane return the scrollArea
	 */
	public JScrollPane getScrollArea() {
		return scrollArea;
	}

	/**
	 * @param scrollArea the scrollArea to set
	 */
	public void setScrollArea(JScrollPane scrollArea) {
		this.scrollArea = scrollArea;
	}

	/**
	 * @param textPane the textPane to set
	 */
	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}

	/**
	 * @return JSONObject return the jsonObject
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * @param jsonObject the jsonObject to set
	 */
	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	/**
	 * @return JTextPane return the textPane
	 */
	public JTextPane getTextPane() {
		return textPane;
	}

}
