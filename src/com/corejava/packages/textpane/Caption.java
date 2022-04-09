package com.corejava.packages.textpane;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Caption {
    private String text;
    private JTextPane textPane;

    public Caption(String text, JTextPane textPane) {
        this.text = text;
        this.textPane = textPane;
        try {
            textPane.setContentType("text/plain");
            StyledDocument document = (StyledDocument) textPane.getDocument();
            Style style = textPane.addStyle("", null);
            StyleConstants.setForeground(style, textPane.getForeground().darker());
            StyleConstants.setItalic(style, true);
            document.insertString(document.getLength(), "Caption: " + text, style);
            document.insertString(document.getLength(), "\n\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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
