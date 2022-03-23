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

import net.miginfocom.swing.MigLayout;

public class Quiz {
    private JTextPane textPane;

    public Quiz(JTextPane textPane) {
        this.textPane = textPane;
        // Convert the text pane's content type to plain text
        textPane.setContentType("text/plain");
    }

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that a multiple choice ComboBox can be added to it
     * alongside a button to submit the response
     * 
     * @param options The options list to add to the multiple choice box options list
     * @param answer The answer to compare the response to so whether or not the answer is correct
     *        or incorrect is determined
     */
    public void generateMultipleChoice(List<String> options, String answer, String feedbackRight,
            String feedbackWrong, JTextPane feedbackArea) {
        try {
            StyledDocument document = (StyledDocument) textPane.getDocument();
            // Create a JPanel to add the multiple choice box and the button to - Uses MigLayout so
            // that the button is displayed besides the multiple choice box
            JPanel panel = new JPanel();
            panel.setLayout(new MigLayout());
            // Create a ComboBox and addd all of the options in the options list to the box
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
                        if (feedbackRight != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackRight);
                        }
                    } else {
                        if (feedbackWrong != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackWrong);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that a text field used for the user response can be
     * added to it alongside a button to submit the response
     * 
     * @param answer The answer to compare the response to so whether or not the answer is correct
     *        or incorrect is determined
     */
    public void generateOpenChoice(String answer, String feedbackRight, String feedbackWrong,
            JTextPane feedbackArea) {
        try {
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
                        if (feedbackRight != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackRight);
                        }
                    } else {
                        if (feedbackWrong != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackWrong);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that a true and a false button can be displayed and
     * compares the users button clicked option to the answer to determine if the answer is correct
     * 
     * @param answer The answer to compare the response to so whether or not the answer is correct
     *        or incorrect is determined
     */
    public void generateTrueFalse(String answer, String feedbackRight, String feedbackWrong,
            JTextPane feedbackArea) {
        try {
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
                        if (feedbackRight != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackRight);
                        }
                    } else {
                        if (feedbackWrong != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackWrong);
                        }
                    }
                }
            });
            // Add an action listener to the false button so the response can be retrieved and
            // compared to the correct answer to determine if the statement is false
            falseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (answer.equalsIgnoreCase("false")) {
                        if (feedbackRight != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackRight);
                        }
                    } else {
                        if (feedbackWrong != null) {
                            feedbackArea.removeAll();
                            feedbackArea.setText(feedbackWrong);
                        }
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
