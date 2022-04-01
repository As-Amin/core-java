package com.corejava.packages.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.StyledDocument;
import org.json.JSONArray;
import org.json.JSONObject;

import com.corejava.packages.Main;
import com.corejava.packages.json.JSONParser;
import com.corejava.packages.screens.Home;
import com.corejava.packages.textpane.Caption;
import com.corejava.packages.textpane.Image;
import com.corejava.packages.textpane.MultipleChoiceQuiz;
import com.corejava.packages.textpane.OpenChoiceQuiz;
import com.corejava.packages.textpane.TrueFalseQuiz;
import com.corejava.packages.textpane.Table;
import com.corejava.packages.textpane.PlainText;
import com.corejava.packages.textpane.Question;
import com.corejava.packages.textpane.Subheading;

public class LearnArea extends JScrollPane {
	private JSONObject jsonObject;
	private JSONParser jsonParser;
	private JTextPane textPane = new JTextPane();

	public LearnArea() {
		this.setViewportView(textPane);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textPane.setEditable(false);
	}

	public void clearAll() {
		textPane.setText(null);
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
		textPane.setCaretPosition(0); // Scroll to the top after adding components
	}

	private void parseJsonFile() {
		try {
			JSONArray allParagraphs = jsonObject.getJSONArray("paragraphs");
			for (int i = 0; i < allParagraphs.length(); i++) {
				String subheading = allParagraphs.getJSONObject(i).getString("subheading");
				String paragraphContent = allParagraphs.getJSONObject(i).getString("content");
				// Append subheading
				if (subheading.length() != 0) {
					if (i != 0) {
						StyledDocument document = (StyledDocument) textPane.getDocument();
						document.insertString(document.getLength(), "\n", null);
					}
					Subheading heading =
							new Subheading(subheading, Main.ACCENT_COLOR, textPane, true);
				}
				// Append paragraph content
				if (paragraphContent.length() != 0) {
					PlainText paragraph = new PlainText(paragraphContent, null, textPane);
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

				try {
					parseTables(allParagraphs.getJSONObject(i).getJSONArray("tables"));
				} catch (Exception e) {
					System.out.println("No tables found for paragraph " + i);
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
			Image image =
					new Image(new File(Main.IMAGES_DIRECTORY + imagesUrlList.get(i)), textPane);
			Caption caption = new Caption(("Caption: " + captionsList.get(i)),
					Main.SECONDARY_ACCENT_COLOR, textPane);
		}
	}

	private void parseTables(JSONArray jsonArray) {
		List<String> columns = null;
		List<String> rows = null;
		// Loop each table
		for (int i = 0; i < jsonArray.length(); i++) {
			// Parse all columns in current table
			JSONArray columnsArray = jsonArray.getJSONObject(i).getJSONArray("columns");
			columns = jsonParser.readArray(columnsArray, "column");
			// Parse all rows in current table
			JSONArray rowsArray = jsonArray.getJSONObject(i).getJSONArray("rows");
			rows = jsonParser.readArray(rowsArray, "row");
			// Split the row cells by comma
			List<String[]> rowsSplit = new ArrayList<String[]>();
			for (int j = 0; j < rows.size(); j++) {
				String rowsSplitCells[] = rows.get(j).split("\\,", columns.size());
				rowsSplit.add(rowsSplitCells);
			}
			// Create the current table object and append to textpane
			Table table = new Table(textPane, rowsSplit.toArray(new Object[][] {}),
					columns.toArray(new String[] {}), false);
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
			Question question = new Question(questions.get(i).toString(),
					Main.SECONDARY_ACCENT_COLOR, textPane);
			MultipleChoiceQuiz multipleChoice =
					new MultipleChoiceQuiz(textPane, options, answers.get(i), feedbackRights.get(i),
							feedbackWrongs.get(i), Home.topicFeedbackArea.getTextPane());
		}
	}

	private void parseOpenChoice(JSONArray jsonArray) {
		List<String> questions = jsonParser.readArray(jsonArray, "question");
		List<String> answers = jsonParser.readArray(jsonArray, "answer");
		List<String> feedbackRights = jsonParser.readArray(jsonArray, "feedbackRight");
		List<String> feedbackWrongs = jsonParser.readArray(jsonArray, "feedbackWrong");
		for (int i = 0; i < questions.size(); i++) {
			Question question =
					new Question(questions.get(i), Main.SECONDARY_ACCENT_COLOR, textPane);
			OpenChoiceQuiz openChoice =
					new OpenChoiceQuiz(textPane, answers.get(i), feedbackRights.get(i),
							feedbackWrongs.get(i), Home.topicFeedbackArea.getTextPane());
		}
	}

	private void parseTrueFalse(JSONArray jsonArray) {
		List<String> questions = jsonParser.readArray(jsonArray, "question");
		List<String> answers = jsonParser.readArray(jsonArray, "answer");
		List<String> feedbackRights = jsonParser.readArray(jsonArray, "feedbackRight");
		List<String> feedbackWrongs = jsonParser.readArray(jsonArray, "feedbackWrong");
		for (int i = 0; i < questions.size(); i++) {
			Question question =
					new Question(questions.get(i), Main.SECONDARY_ACCENT_COLOR, textPane);
			TrueFalseQuiz trueFalse =
					new TrueFalseQuiz(textPane, answers.get(i), feedbackRights.get(i),
							feedbackWrongs.get(i), Home.topicFeedbackArea.getTextPane());
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
