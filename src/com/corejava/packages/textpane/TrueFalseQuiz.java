package com.corejava.packages.textpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import com.corejava.packages.ui.ScrollTextPane;
import net.miginfocom.swing.MigLayout;

public class TrueFalseQuiz {
    private JTextPane textPane;

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that a true and a false button can be displayed and
     * compares the users button clicked option to the answer to determine if the answer is correct
     * 
     * @param answer The answer to compare the response to so whether or not the answer is correct
     *        or incorrect is determined
     */
    public TrueFalseQuiz(JTextPane textPane, String answer, String feedbackRight,
            String feedbackWrong, ScrollTextPane topicFeedbackArea) {
        this.textPane = textPane;
        try {
            // Convert the text pane's content type to plain text
            textPane.setContentType("text/plain");
            StyledDocument document = (StyledDocument) textPane.getDocument();
            // Create a JPanel to add the true and false buttons to - Uses MigLayout so
            // that the buttons are displayed side to side and dont wrap to a new line
            JPanel panel = new JPanel();
            panel.setLayout(new MigLayout());
            // Create a true button which when clicked on registers the users answer as true
            JButton trueButton = new JButton("True");
            panel.add(panel.add(trueButton));
            // Create a false button which when clicked on registers the users answer as false
            JButton falseButton = new JButton("False");
            panel.add(panel.add(falseButton));
            // Add the panel to the TextPane
            textPane.insertComponent(panel);
            document.insertString(document.getLength(), "\n\n", null);
            // Add an action listener to the true button so the response can be retrieved and
            // compared to the correct answer to determine if the statement is true
            trueButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (answer.equalsIgnoreCase("true")) {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText(feedbackRight);

                    } else {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText(feedbackWrong);
                    }
                }
            });
            // Add an action listener to the false button so the response can be retrieved and
            // compared to the correct answer to determine if the statement is false
            falseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (answer.equalsIgnoreCase("false")) {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText(feedbackRight);

                    } else {
                        topicFeedbackArea.removeAll();
                        topicFeedbackArea.setText(feedbackWrong);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
