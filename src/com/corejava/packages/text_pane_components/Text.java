package com.corejava.packages.text_pane_components;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.io.IOException;

public class Text {
    private JTextPane textPane;
    private String text;
    private Color color;
    private int fontSize;
    private String fontName;
    private Boolean isQuestion;

    public Text(JTextPane textPane, String text, Color color, int fontSize, String fontName,
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

    /**
     * @return Color return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return int return the fontSize
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * @return String return the fontName
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * @param fontName the fontName to set
     */
    public void setFontName(String fontName) {
        this.fontName = fontName;
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

}
