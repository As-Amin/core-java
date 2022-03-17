package com.corejava.packages.ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.Main;
import com.corejava.packages.home.Home;
import com.corejava.packages.json.JSONParser;
import com.corejava.packages.textpane_ui.Image;
import com.corejava.packages.textpane_ui.Quiz;
import com.corejava.packages.textpane_ui.Text;

public class LearnArea extends JTextPane {
	private JSONObject jsonObject;
	private JSONParser jsonParser;
	private JScrollPane scrollArea;

	public JScrollPane generate() {
		scrollArea = new JScrollPane(this);
		scrollArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setEditable(false);
		return scrollArea;
	}

	public void clearAll() {
		this.setText(null);
	}

	public void openFile(File topicFile) {
		clearAll();
		try {
			jsonParser = new JSONParser(topicFile);
			jsonObject = jsonParser.generateJSONObject();
			parseJsonFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setCaretPosition(0); // Scroll to the top after adding components
	}

	private void parseJsonFile() {
		try {
			JSONArray allParagraphs = jsonObject.getJSONArray("paragraphs");
			for (int i = 0; i < allParagraphs.length(); i++) {
				String subheading = allParagraphs.getJSONObject(i).getString("subheading");
				String paragraphContent = allParagraphs.getJSONObject(i).getString("content");
				// Append subheading
				if (subheading.length() != 0) {
					Text textPaneSubheading =
							new Text(subheading, Main.SECONDARY_ACCENT_COLOR, this);
					textPaneSubheading.generateText();
				}
				// Append paragraph content
				if (paragraphContent.length() != 0) {
					Text textPaneParagraph = new Text(paragraphContent, null, this);
					textPaneParagraph.generateText();
				}
				try {
					parseImages(allParagraphs.getJSONObject(i).getJSONArray("images"));
				} catch (Exception e) {
					System.out.println("No images found for paragraph " + i);
				}

				try {
					parseOpenChoice(allParagraphs.getJSONObject(i).getJSONArray("openChoiceQuiz"));
				} catch (Exception e) {
					System.out.println("No open choice quiz found for paragraph " + i);
				}

				try {
					parseTrueFalse(allParagraphs.getJSONObject(i).getJSONArray("trueFalseQuiz"));
				} catch (Exception e) {
					System.out.println("No true false quiz found for paragraph " + i);
				}

				try {
					parseMultipleChoice(
							allParagraphs.getJSONObject(i).getJSONArray("multipleChoiceQuiz"));
				} catch (Exception e) {
					System.out.println("No multiple choice quiz found for paragraph " + i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseImages(JSONArray jsonArray) {
		// Convert images array contents to string array lists (image urls and captions)
		List<String> imagesUrlList = jsonParser.readArray(jsonArray, "url");
		List<String> captionsList = jsonParser.readArray(jsonArray, "caption");
		for (int i = 0; i < imagesUrlList.size(); i++) {
			Image image = new Image(new File(Main.IMAGES_DIRECTORY + imagesUrlList.get(i)), this);
			image.generate();
			Text caption = new Text(("Caption: " + captionsList.get(i)), Main.ACCENT_COLOR, this);
			caption.generateText();
		}
	}

	private void parseMultipleChoice(JSONArray jsonArray) {
		List<String> questions = jsonParser.readArray(jsonArray, "question");
		List<String> answers = jsonParser.readArray(jsonArray, "answer");
		List<String> options = null;
		List<String> feedbackRights = jsonParser.readArray(jsonArray, "feedbackRight");
		List<String> feedbackWrongs = jsonParser.readArray(jsonArray, "feedbackWrong");
		for (int i = 0; i < questions.size(); i++) {
			JSONArray optionsArray = jsonArray.getJSONObject(i).getJSONArray("options");
			options = jsonParser.readArray(optionsArray, "option");
			Text question = new Text(questions.get(i).toString(), Main.ACCENT_COLOR, this);
			question.generateQuestion();
			Quiz multipleChoice = new Quiz(this);
			multipleChoice.generateMultipleChoice(options, answers.get(i), feedbackRights.get(i),
					feedbackWrongs.get(i), Home.topicFeedbackArea);
		}
	}

	private void parseOpenChoice(JSONArray jsonArray) {
		List<String> questions = jsonParser.readArray(jsonArray, "question");
		List<String> answers = jsonParser.readArray(jsonArray, "answer");
		List<String> feedbackRights = jsonParser.readArray(jsonArray, "feedbackRight");
		List<String> feedbackWrongs = jsonParser.readArray(jsonArray, "feedbackWrong");
		for (int i = 0; i < questions.size(); i++) {
			Text question = new Text(questions.get(i), Main.ACCENT_COLOR, this);
			question.generateQuestion();
			Quiz openChoice = new Quiz(this);
			openChoice.generateOpenChoice(answers.get(i), feedbackRights.get(i),
					feedbackWrongs.get(i), Home.topicFeedbackArea);
		}
	}

	private void parseTrueFalse(JSONArray jsonArray) {
		List<String> questions = jsonParser.readArray(jsonArray, "question");
		List<String> answers = jsonParser.readArray(jsonArray, "answer");
		List<String> feedbackRights = jsonParser.readArray(jsonArray, "feedbackRight");
		List<String> feedbackWrongs = jsonParser.readArray(jsonArray, "feedbackWrong");
		for (int i = 0; i < questions.size(); i++) {
			Text question = new Text(questions.get(i), Main.ACCENT_COLOR, this);
			question.generateQuestion();
			Quiz trueFalse = new Quiz(this);
			trueFalse.generateTrueFalse(answers.get(i), feedbackRights.get(i),
					feedbackWrongs.get(i), Home.topicFeedbackArea);
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

	public JTextPane getTextPane() {
		return this;
	}
}
