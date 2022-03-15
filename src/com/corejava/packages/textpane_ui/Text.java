package com.corejava.packages.textpane_ui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Text {
    private Color foreground;
    private Boolean isQuestion;
    private String text;
    private JTextPane textPane;

    public Text(String text, Color foreground, Boolean isQuestion, JTextPane textPane) {
        this.text = text;
        this.foreground = foreground;
        this.isQuestion = isQuestion;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException, IOException {
        appendText();
    }

    private void appendText() throws BadLocationException {
        textPane.setContentType("text/plain");
        StyledDocument document = (StyledDocument) textPane.getDocument();
        Style style = textPane.addStyle("", null);
        if (foreground != null) {
            StyleConstants.setForeground(style, foreground);
        }
        document.insertString(document.getLength(), text, style);
        if (isQuestion) {
            document.insertString(document.getLength(), "\n", null);
        } else {
            document.insertString(document.getLength(), "\n\n", null);
        }
    }

    /**
     * @return Color return the foreground
     */
    public Color getForeground() {
        return foreground;
    }

    /**
     * @return Boolean return the isQuestion
     */
    public Boolean isIsQuestion() {
        return isQuestion;
    }

    /**
     * @param isQuestion the isQuestion to set
     */
    public void setIsQuestion(Boolean isQuestion) {
        this.isQuestion = isQuestion;
    }

    /**
     * @return String return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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
