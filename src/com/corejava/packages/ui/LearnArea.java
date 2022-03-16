package com.corejava.packages.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import org.apache.commons.configuration.ConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.Main;
import com.corejava.packages.json.JSONParser;
import com.corejava.packages.textpane_ui.Image;
import com.corejava.packages.textpane_ui.MultipleChoice;
import com.corejava.packages.textpane_ui.OpenChoice;
import com.corejava.packages.textpane_ui.Text;
import com.corejava.packages.textpane_ui.TrueFalse;

public class LearnArea {
	private JSONObject jsonObject;
	private JSONParser jsonParser;
	private JScrollPane scrollArea;
	private JTextPane textPane;

	public JScrollPane Generate() {
		textPane = new JTextPane();
		scrollArea = new JScrollPane(textPane);
		scrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollArea.getVerticalScrollBar().setUnitIncrement(10);
		textPane.setEditable(false);
		return scrollArea;
	}

	public void ClearAll() {
		textPane.setText(null);
	}

	public void OpenFile(File topicFile) throws IOException, ConfigurationException {
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
					Text textPaneSubheading =
							new Text(subheading, Main.SECONDARY_ACCENT_COLOR, false, textPane);
					textPaneSubheading.Generate();
				}
				// Append paragraph content
				if (paragraphContent.length() != 0) {
					Text textPaneParagraph = new Text(paragraphContent, null, false, textPane);
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

	private void parseImages(JSONArray jsonArray)
			throws BadLocationException, IOException, ConfigurationException {
		// Convert images array contents to string array lists (image urls and captions)
		List<String> imagesUrlList = jsonParser.ReadArray(jsonArray, "url");
		List<String> captionsList = jsonParser.ReadArray(jsonArray, "caption");
		for (int i = 0; i < imagesUrlList.size(); i++) {
			Image textPaneImage = new Image(imagesUrlList.get(i), textPane);
			textPaneImage.Generate();
			Text textPaneCaption = new Text(("Caption: " + captionsList.get(i)), Main.ACCENT_COLOR,
					false, textPane);
			textPaneCaption.Generate();
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
			Text textPaneQuestion =
					new Text(questions.get(i).toString(), Main.ACCENT_COLOR, true, textPane);
			textPaneQuestion.Generate();
			MultipleChoice multipleChoiceQuiz =
					new MultipleChoice(options, answers.get(i).toString(), textPane);
			multipleChoiceQuiz.Generate();
		}
	}

	private void parseOpenChoice(JSONArray jsonArray) throws BadLocationException, IOException {
		List<String> questions = jsonParser.ReadArray(jsonArray, "question");
		List<String> answers = jsonParser.ReadArray(jsonArray, "answer");
		for (int i = 0; i < questions.size(); i++) {
			Text textPaneQuestion = new Text(questions.get(i), Main.ACCENT_COLOR, true, textPane);
			textPaneQuestion.Generate();
			OpenChoice openChoice = new OpenChoice(answers.get(i), textPane);
			openChoice.Generate();
		}
	}

	private void parseTrueFalse(JSONArray jsonArray) throws BadLocationException, IOException {
		List<String> questions = jsonParser.ReadArray(jsonArray, "question");
		List<String> answers = jsonParser.ReadArray(jsonArray, "answer");
		for (int i = 0; i < questions.size(); i++) {
			Text textPaneQuestion = new Text(questions.get(i), Main.ACCENT_COLOR, true, textPane);
			textPaneQuestion.Generate();
			TrueFalse trueFalseQuiz = new TrueFalse(answers.get(i), textPane);
			trueFalseQuiz.Generate();
		}
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
}
