package com.corejava.packages.textpane_components;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import com.corejava.packages.colors.Colors;
import java.io.IOException;

public class QuestionText {
    private JTextPane textPane;
    private String text;

    public QuestionText(JTextPane textPane, String text) {
        this.textPane = textPane;
        this.text = text;
    }

    public void Generate() throws BadLocationException, IOException {
        appendText();
    }

    private void appendText() throws BadLocationException {
        textPane.setContentType("text/plain");
        StyledDocument document = (StyledDocument) textPane.getDocument();
        Style style = textPane.addStyle("", null);
        StyleConstants.setForeground(style, Colors.THEME_SECONDARY.getColor());
        document.insertString(document.getLength(), text, style);
        document.insertString(document.getLength(), "\n", null);
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
}
