package com.corejava.packages.learn_content;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
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
        panel.add(panel.add(configureButton(trueButton)));

        JButton falseButton = new JButton("False");
        panel.add(panel.add(configureButton(falseButton)));

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

    private JButton configureButton(JButton button) {
        button.setForeground(Colors.WHITE.getColor());
        button.setBackground(Colors.THEME.getColor());
        button.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
        return button;
    }
}
