package com.corejava.packages.text_pane_components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import com.corejava.packages.colors.Colors;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import net.miginfocom.swing.MigLayout;

public class MultipleChoiceQuiz {
    private ArrayList<String> options;
    private String answer;
    private JTextPane textPane;

    public MultipleChoiceQuiz(ArrayList<String> options, String answer, JTextPane textPane) {
        this.options = options;
        this.answer = answer;
        this.textPane = textPane;
    }

    public void Generate() throws BadLocationException {
        appendMultipleChoiceQuiz();
    }

    private void appendMultipleChoiceQuiz() throws BadLocationException {
        StyledDocument document = (StyledDocument) textPane.getDocument();

        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setLayout(new MigLayout());

        JComboBox<String> optionsComboBox = new JComboBox(options.toArray());
        panel.add(configureComboBox(optionsComboBox));

        JButton submitButton = new JButton("Submit");
        panel.add(panel.add(configureButton(submitButton)));

        textPane.insertComponent(panel);
        document.insertString(document.getLength(), "\n\n", null);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (answer.equalsIgnoreCase(optionsComboBox.getSelectedItem().toString())) {
                    System.out.println("Correct");
                } else {
                    System.out.println("Hey wrong answer");
                }
            }
        });
    }

    private JComboBox<String> configureComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
        return comboBox;
    }

    private JButton configureButton(JButton button) {
        button.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.TOPIC_TEXT.getFS()));
        return button;
    }
}
