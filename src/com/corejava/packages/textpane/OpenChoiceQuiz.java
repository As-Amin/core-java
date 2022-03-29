package com.corejava.packages.textpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import com.corejava.packages.ui.ScrollTextPane;
import net.miginfocom.swing.MigLayout;

public class OpenChoiceQuiz {
    private JTextPane textPane;

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that a text field used for the user response can be
     * added to it alongside a button to submit the response
     * 
     * @param answer The answer to compare the response to so whether or not the answer is correct
     *        or incorrect is determined
     */
    public OpenChoiceQuiz(JTextPane textPane, String answer, String feedbackRight,
            String feedbackWrong, ScrollTextPane topicFeedbackArea) {
        this.textPane = textPane;
        try {
            // Convert the text pane's content type to plain text
            textPane.setContentType("text/plain");
            StyledDocument document = (StyledDocument) textPane.getDocument();
            // Create a JPanel to add the open choice box and the button to - Uses MigLayout so
            // that the button is displayed besides the text field
            JPanel panel = new JPanel();
            panel.setLayout(new MigLayout());
            // Create a text field for the user to enter their answer into, add a placeholder and
            // add to the panel
            JTextField answerField = new JTextField();
            panel.add(addTextFieldPlaceholder(answerField, "Enter answer here..."),
                    "wmin 70%, grow");
            // Create a button so that the user can submit their response
            JButton submitButton = new JButton("Submit");
            panel.add(submitButton, "shrink");
            // Add the panel to the TextPane
            textPane.insertComponent(panel);
            document.insertString(document.getLength(), "\n\n", null);
            // Add an action listener to the button so the response can be retrieved and compared to
            // the correct answer to determine if correct or incorrect
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (answerField.getText().equalsIgnoreCase(answer)) {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText(feedbackRight);

                    } else if (answerField.getText().length() > 0) {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText(feedbackWrong);
                    } else {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText("Enter your answer in the box!");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a placeholder to the textfield passed in to the function. Updates the client property
     * (theming properties)
     * 
     * @param textField The text field to add a placeholder to
     * @param placeholderText The placeholder text to add to the text field
     * @return textField The modified text field with the placeholder added to it
     */
    private JTextField addTextFieldPlaceholder(JTextField textField, String placeholderText) {
        textField.putClientProperty("JTextField.placeholderText", placeholderText);
        return textField;
    }
}
