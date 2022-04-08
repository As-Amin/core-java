package com.corejava.packages.textpane;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Heading {
    private Color foreground;
    private String text;
    private JTextPane textPane;
    private Boolean largerFontSize;

    public Heading(String text, Color foreground, JTextPane textPane, Boolean largerFontSize) {
        this.text = text;
        this.foreground = foreground;
        this.textPane = textPane;
        this.largerFontSize = largerFontSize;
        try {
            textPane.setContentType("text/plain");
            StyledDocument document = (StyledDocument) textPane.getDocument();
            Style style = textPane.addStyle("", null);
            if (foreground != null) {
                StyleConstants.setForeground(style, foreground);
            }
            if (largerFontSize) {
                StyleConstants.setFontSize(style, StyleConstants.getFontSize(style) + 6);
            }
            document.insertString(document.getLength(), text, style);
            document.insertString(document.getLength(), "\n", null);
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            textPane.insertComponent(separator);
            document.insertString(document.getLength(), "\n\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return Color return the foreground
     */
    public Color getForeground() {
        return foreground;
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

    /**
     * @return Boolean return the largerFontSize
     */
    public Boolean isLargerFontSize() {
        return largerFontSize;
    }

    /**
     * @param largerFontSize the largerFontSize to set
     */
    public void setLargerFontSize(Boolean largerFontSize) {
        this.largerFontSize = largerFontSize;
    }

}
