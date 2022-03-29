package com.corejava.packages.textpane;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.StyledDocument;

public class Table extends JTable {
    private JTextPane textPane;
    private Object[][] rows;
    private String[] columns;
    private Boolean editable;

    public Table(JTextPane textPane, Object[][] rows, String[] columns, Boolean editable) {
        this.textPane = textPane;
        this.rows = rows;
        this.columns = columns;
        this.editable = editable;
        try {
            textPane.setContentType("text/plain");
            StyledDocument document = (StyledDocument) textPane.getDocument();
            TableModel tableModel = new DefaultTableModel(rows, columns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    if (editable == false) {
                        return false;
                    } else {
                        return true;
                    }
                }
            };
            this.setModel(tableModel);
            this.removeEditor();
            this.setPreferredScrollableViewportSize(this.getPreferredSize());
            if (editable == false) {

            }
            textPane.insertComponent(new JScrollPane(this));
            document.insertString(document.getLength(), "\n\n", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
