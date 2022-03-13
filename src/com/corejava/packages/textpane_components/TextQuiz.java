package com.corejava.packages.textpane_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import net.miginfocom.swing.MigLayout;

public class TextQuiz {
    private String answer;
    private JTextPane textPane;

    public TextQuiz(String answer, JTextPane textPane) {
        this.answer = answer;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException {
        appendTextQuiz();
    }

    private void appendTextQuiz() throws BadLocationException {
        StyledDocument document = (StyledDocument) textPane.getDocument();

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setLayout(new MigLayout());

        JTextField answerField = new JTextField();
        panel.add(configureTextField(answerField), "wmin 70%, grow");

        JButton submit = new JButton("Submit");
        panel.add(submit, "shrink");

        textPane.insertComponent(panel);
        document.insertString(document.getLength(), "\n\n", null);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // If the answer contains the text that is in the answer string from the JSON
                // file, it is correct.
                if (answerField.getText().equalsIgnoreCase(answer)) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Hey wrong answer");
                }
            }
        });
    }

    private JTextField configureTextField(JTextField textField) {
        textField.putClientProperty("JTextField.placeholderText", "Your answer here...");
        return textField;
    }


    /**
     * @return String return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
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
