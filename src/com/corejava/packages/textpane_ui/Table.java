package com.corejava.packages.textpane_ui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.StyledDocument;

public class Table {

    private JTable table;
    private JTextPane textPane;

    private Object[][] rows;
    private String[] columns;

    public Table(JTextPane textPane, Object[][] rows, String[] columns) {
        this.textPane = textPane;
        this.rows = rows;
        this.columns = columns;
        textPane.setContentType("text/plain");
    }

    public void generate() {
        try {
            StyledDocument document = (StyledDocument) textPane.getDocument();
            TableModel tableModel = new DefaultTableModel(rows, columns);
            table = new JTable(tableModel);

            table.setPreferredScrollableViewportSize(table.getPreferredSize());

            textPane.insertComponent(new JScrollPane(table));
            document.insertString(document.getLength(), "\n\n", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
