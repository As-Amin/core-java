package com.corejava.packages.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import com.corejava.packages.json.JSONParser;
import com.corejava.packages.textpane_ui.CaptionText;
import com.corejava.packages.textpane_ui.Image;
import com.corejava.packages.textpane_ui.MultipleChoice;
import com.corejava.packages.textpane_ui.OpenChoice;
import com.corejava.packages.textpane_ui.QuestionText;
import com.corejava.packages.textpane_ui.SubheadingText;
import com.corejava.packages.textpane_ui.Text;
import com.corejava.packages.textpane_ui.TrueFalse;
import org.json.JSONArray;
import org.json.JSONObject;

public class LearnArea {
	private JScrollPane scrollArea;
	private JTextPane textPane;
	private JSONObject jsonObject;
	private JSONParser jsonParser;

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
		jsonParser = new JSONParser(topicFile);
		jsonObject = jsonParser.GenerateJSONObject();
		parseJsonFile();
		textPane.setCaretPosition(0); // Scroll to the top after adding components
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
				parseOpenChoice(allParagraphs.getJSONObject(i).getJSONArray("openChoiceQuiz"));
				parseTrueFalse(allParagraphs.getJSONObject(i).getJSONArray("trueFalseQuiz"));
				parseMultipleChoice(
						allParagraphs.getJSONObject(i).getJSONArray("multipleChoiceQuiz"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void parseImages(JSONArray jsonArray) throws BadLocationException, IOException {
		// Convert images array contents to string array lists (image urls and captions)
		List<String> imagesUrlList = jsonParser.ReadArray(jsonArray, "url");
		List<String> captionsList = jsonParser.ReadArray(jsonArray, "caption");
		for (int i = 0; i < imagesUrlList.size(); i++) {
			Image textPaneImage = new Image(imagesUrlList.get(i), textPane);
			textPaneImage.Generate();
			CaptionText textPaneCaption =
					new CaptionText(textPane, ("Caption: " + captionsList.get(i)));
			textPaneCaption.Generate();
		}
	}

	private void parseOpenChoice(JSONArray jsonArray) throws BadLocationException, IOException {
		List<String> questions = jsonParser.ReadArray(jsonArray, "question");
		List<String> answers = jsonParser.ReadArray(jsonArray, "answer");
		for (int i = 0; i < questions.size(); i++) {
			QuestionText textPaneQuestion = new QuestionText(questions.get(i), textPane);
			textPaneQuestion.Generate();
			OpenChoice openChoice = new OpenChoice(answers.get(i), textPane);
			openChoice.Generate();
		}
	}

	private void parseTrueFalse(JSONArray jsonArray) throws BadLocationException, IOException {
		List<String> questions = jsonParser.ReadArray(jsonArray, "question");
		List<String> answers = jsonParser.ReadArray(jsonArray, "answer");
		for (int i = 0; i < questions.size(); i++) {
			QuestionText textPaneQuestion = new QuestionText(questions.get(i), textPane);
			textPaneQuestion.Generate();
			TrueFalse trueFalseQuiz = new TrueFalse(answers.get(i), textPane);
			trueFalseQuiz.Generate();
		}
	}

	private void parseMultipleChoice(JSONArray jsonArray) throws BadLocationException, IOException {
		List<String> questions = jsonParser.ReadArray(jsonArray, "question");
		List<String> answers = jsonParser.ReadArray(jsonArray, "answer");
		List<String> options = null;

		for (int i = 0; i < questions.size(); i++) {
			JSONArray optionsArray = jsonArray.getJSONObject(i).getJSONArray("options");
			options = jsonParser.ReadArray(optionsArray, "option");
		}

		for (int i = 0; i < questions.size(); i++) {
			QuestionText textPaneQuestion = new QuestionText(questions.get(i).toString(), textPane);
			textPaneQuestion.Generate();
			MultipleChoice multipleChoiceQuiz =
					new MultipleChoice(options, answers.get(i).toString(), textPane);
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
	 * @return JTextPane return the textPane
	 */
	public JTextPane getTextPane() {
		return textPane;
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
	 * @return JSONParser return the jsonParser
	 */
	public JSONParser getJsonParser() {
		return jsonParser;
	}

	/**
	 * @param jsonParser the jsonParser to set
	 */
	public void setJsonParser(JSONParser jsonParser) {
		this.jsonParser = jsonParser;
	}

}
