package com.corejava.packages.textpane;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Image {
    private File imageFile; // The image file to add to the text pane
    private JTextPane textPane; // The text pane to append the image to

    /**
     * Constructor which sets the image file and the TextPane - used to generate the image and add
     * this to the TextPane
     * 
     * @param imageFile The image file to add to the text pane
     * @param textPane The TextPane to append the image to
     */
    public Image(File imageFile, JTextPane textPane) {
        this.imageFile = imageFile;
        this.textPane = textPane;
        // Convert the text pane's content type to plain text
        textPane.setContentType("text/plain");
    }

    /**
     * Converts the TextPane content type to plain text, retrieves the document from the TextPane
     * and converts it to a StyledDocument so that the image file passed into the constructor can be
     * generated and appended to the TextPane
     */
    public void generate() {
        try {
            StyledDocument document = (StyledDocument) textPane.getDocument();
            BufferedImage BI = ImageIO.read(imageFile);
            ImageIcon image = new ImageIcon(BI);
            Style style = document.addStyle("", null);
            StyleConstants.setIcon(style, image);
            document.insertString(document.getLength(), "String", style);
            document.insertString(document.getLength(), "\n\n", null);
        } catch (Exception e) {
            e.printStackTrace();
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

}
