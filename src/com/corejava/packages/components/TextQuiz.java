package com.corejava.packages.components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import net.miginfocom.swing.MigLayout;

public class TextQuiz {
    private String answer;
    private JTextPane textPane;

    public TextQuiz(String answer, JTextPane textPane) {
        this.answer = answer;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException {
        appendTextQuiz();
    }

    private void appendTextQuiz() throws BadLocationException {
        StyledDocument document = (StyledDocument) textPane.getDocument();

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setLayout(new MigLayout());

        JTextField answerField = new JTextField();
        panel.add(configureTextField(answerField), "wmin 70%, grow");

        JButton submit = new JButton("Submit");
        panel.add(configureButton(submit), "shrink");

        textPane.insertComponent(panel);
        document.insertString(document.getLength(), "\n\n", null);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // If the answer contains the text that is in the answer string from the JSON
                // file, it is correct.
                if (answerField.getText().equalsIgnoreCase(answer)) {
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

    private JTextField configureTextField(JTextField textField) {
        textField.putClientProperty("JTextField.placeholderText", "Your answer here...");
        textField.setBackground(Colors.BACKGROUND.getColor());
        textField.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
        return textField;
    }

}
