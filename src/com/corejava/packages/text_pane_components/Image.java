package com.corejava.packages.text_pane_components;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import com.corejava.packages.home.Main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private String imageURL;
    private JTextPane textPane;

    public Image(String imageURL, JTextPane textPane) {
        this.imageURL = imageURL;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException, IOException {
        appendImage();
    }

    private void appendImage() throws BadLocationException, IOException {
        StyledDocument document = (StyledDocument) textPane.getDocument();
        BufferedImage BI = ImageIO.read(new File(Main.getImagesDirectory() + imageURL));
        ImageIcon image = new ImageIcon(BI);
        Style style = document.addStyle("", null);
        StyleConstants.setIcon(style, image);
        document.insertString(document.getLength(), "String", style);
        document.insertString(document.getLength(), "\n\n", null);
    }
}
