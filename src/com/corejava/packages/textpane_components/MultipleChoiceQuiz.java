package com.corejava.packages.textpane_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import net.miginfocom.swing.MigLayout;

public class MultipleChoiceQuiz {
    private ArrayList<String> options;
    private String answer;
    private JTextPane textPane;

    public MultipleChoiceQuiz(ArrayList<String> options, String answer, JTextPane textPane) {
        this.options = options;
        this.answer = answer;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException {
        appendMultipleChoiceQuiz();
    }

    private void appendMultipleChoiceQuiz() throws BadLocationException {
        StyledDocument document = (StyledDocument) textPane.getDocument();

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setLayout(new MigLayout());

        JComboBox<String> optionsComboBox = new JComboBox(options.toArray());
        panel.add(optionsComboBox);

        JButton submitButton = new JButton("Submit");
        panel.add(panel.add(submitButton));

        textPane.insertComponent(panel);
        document.insertString(document.getLength(), "\n\n", null);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (answer.equalsIgnoreCase(optionsComboBox.getSelectedItem().toString())) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Hey wrong answer");
                }
            }
        });
    }

    /**
     * @return ArrayList<String> return the options
     */
    public ArrayList<String> getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(ArrayList<String> options) {
        this.options = options;
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
