package com.corejava.packages.textpane_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import net.miginfocom.swing.MigLayout;

public class TrueFalseQuiz {
    private String answer;
    private JTextPane textPane;

    public TrueFalseQuiz(String answer, JTextPane textPane) {
        this.answer = answer;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException {
        appendTrueFalseQuiz();
    }

    private void appendTrueFalseQuiz() throws BadLocationException {
        StyledDocument document = (StyledDocument) textPane.getDocument();

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setLayout(new MigLayout());

        JButton trueButton = new JButton("True");
        panel.add(panel.add(trueButton));

        JButton falseButton = new JButton("False");
        panel.add(panel.add(falseButton));

        textPane.insertComponent(panel);
        document.insertString(document.getLength(), "\n\n", null);

        trueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // If the answer contains the text that is in the answer string from the JSON
                // file, it is correct.
                if (answer.equalsIgnoreCase("true")) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Hey wrong answer");
                }
            }
        });

        falseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // If the answer contains the text that is in the answer string from the JSON
                // file, it is correct.
                if (answer.equalsIgnoreCase("false")) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Hey wrong answer");
                }
            }
        });
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
