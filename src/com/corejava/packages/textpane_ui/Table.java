package com.corejava.packages.textpane_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class Table {

    private JTable table;
    private JTextPane textPane;

    public Table(JTextPane textPane, String[][] objects, String[] columns) {
        this.textPane = textPane;
        textPane.setContentType("text/plain");
        table = new JTable(objects, columns);
        try {
            StyledDocument document = (StyledDocument) textPane.getDocument();
            textPane.insertComponent(table);
            document.insertString(document.getLength(), "\n\n", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
