package com.corejava.packages.learn_content;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.IOException;

public class TextPaneText {
    private JTextPane textPane;
    private String text;
    private Color color;
    private int fontSize;
    private String fontName;
    private Boolean isQuestion;

    public TextPaneText(JTextPane textPane, String text, Color color, int fontSize, String fontName,
            Boolean isQuestion) {
        this.textPane = textPane;
        this.text = text;
        this.color = color;
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.isQuestion = isQuestion;
    }

    public void Generate() throws BadLocationException, IOException {
        appendText();
    }

    private void appendText() throws BadLocationException {
        StyledDocument document = (StyledDocument) textPane.getDocument();
        Style style = textPane.addStyle("", null);
        StyleConstants.setFontFamily(style, fontName);
        StyleConstants.setFontSize(style, fontSize);
        StyleConstants.setBold(style, true);
        StyleConstants.setForeground(style, color);
        document.insertString(document.getLength(), text, style);
        if (isQuestion == true) {
            document.insertString(document.getLength(), "\n", null);
        } else {
            document.insertString(document.getLength(), "\n\n", null);
        }
    }
}
