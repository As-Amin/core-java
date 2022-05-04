package com.corejava.packages.textpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class MultipleChoiceQuiz {
    private JTextPane textPane;

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that a multiple choice ComboBox can be added to it
     * alongside a button to submit the response
     * 
     * @param options The options list to add to the multiple choice box options list
     * @param answer The answer to compare the response to so whether or not the answer is correct
     *        or incorrect is determined
     */
    public MultipleChoiceQuiz(JTextPane textPane, List<String> options, String answer,
            String feedbackRight, String feedbackWrong, JTextPane feedbackArea) {
        this.textPane = textPane;
        try {
            // Convert the text pane's content type to plain text
            textPane.setContentType("text/plain");
            StyledDocument document = (StyledDocument) textPane.getDocument();
            // Create a JPanel to add the multiple choice box and the button to - Uses MigLayout so
            // that the button is displayed besides the multiple choice box
            JPanel panel = new JPanel();
            panel.setLayout(new MigLayout());
            // Create a ComboBox and add all of the options in the options list to the box
            JComboBox<String> optionsBox = new JComboBox<String>();
            for (Object item : options) {
                optionsBox.addItem(item.toString());
            }
            panel.add(optionsBox);
            // Create a button so that the user can submit their response
            JButton submitButton = new JButton("Submit");
            panel.add(panel.add(submitButton));
            // Add the panel to the TextPane
            textPane.insertComponent(panel);
            document.insertString(document.getLength(), "\n\n", null);
            // Add an action listener to the button so the response can be retrieved and compared to
            // the correct answer to determine if correct or incorrect
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (answer.equalsIgnoreCase(optionsBox.getSelectedItem().toString())) {
                        feedbackArea.removeAll();
                        feedbackArea.setText(feedbackRight);
                    } else {
                        feedbackArea.removeAll();
                        feedbackArea.setText(feedbackWrong);
                    }
                    feedbackArea.setCaretPosition(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
