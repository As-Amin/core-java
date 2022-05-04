package com.corejava.packages.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.comparator.NameFileComparator;

import com.corejava.packages.screens.Home;

public class ListFiles extends JScrollPane {
	private ArrayList<File> allParentFiles = new ArrayList<File>();
	private ArrayList<File> allChildFiles = new ArrayList<File>();
	private String directory;
	private char fileNumberSeperator;
	private JList<String> listPanel;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private String filterKeyword = "";

	public ListFiles(String directory, char fileNumberSeperator) {
		this.directory = directory;
		this.fileNumberSeperator = fileNumberSeperator;
		setupList();
	}

	private void setupList() {
		generateParentStrings();
		generateListeners();
		this.setViewportView(listPanel);
		this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getVerticalScrollBar().setUnitIncrement(10);
	}

	private void generateParentStrings() {
		File[] files = new File(directory).listFiles();
		Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
		for (File file : files) {
			generateChildStrings(file);
		}
	}

	private void generateChildStrings(File sectionDir) {
		for (File file : sectionDir.listFiles()) {
			String fileName = getFileName(file);
			if (filterKeyword != null || filterKeyword.length() != 0) {
				if (fileName.toLowerCase().contains(filterKeyword.toLowerCase())) {
					model.addElement(fileName);
					allParentFiles.add(sectionDir);
					allChildFiles.add(file);
				}
			}
		}
	}

	private void generateListeners() {
		listPanel = new JList<>(model);
		listPanel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					try {
						Home.topicTitleBox.setText(listPanel.getSelectedValue());
						Home.sectionTitleBox.setText(
								getFileName(allParentFiles.get(listPanel.getSelectedIndex())));
						Home.topicLearnArea
								.openFile(allChildFiles.get(listPanel.getSelectedIndex()));
					} catch (Exception e) {
						Home.topicTitleBox.setText("Something went wrong");
						Home.sectionTitleBox.setText("Something went wrong");
						e.printStackTrace();
					}
				}
			}
		});
	}

	private String getFileName(File file) {
		String topicAndFileType[] = file.getName().split("\\.", 2);
		String numberTopic[] = topicAndFileType[0].split("\\" + fileNumberSeperator + " ", 2);
		return numberTopic[1];
	}

	public void filter(String keyword) {
		filterKeyword = keyword;
		clearList();
		setupList();
	}

	public void reset() {
		filterKeyword = "";
		clearList();
		setupList();
	}

	private void clearList() {
		listPanel.clearSelection();
		allParentFiles.clear();
		allChildFiles.clear();
		model.clear();
	}

	/**
	 * @return ArrayList<File> return the allParentFiles
	 */
	public ArrayList<File> getAllParentFiles() {
		return allParentFiles;
	}

	/**
	 * @param allParentFiles the allParentFiles to set
	 */
	public void setAllParentFiles(ArrayList<File> allParentFiles) {
		this.allParentFiles = allParentFiles;
	}

	/**
	 * @return ArrayList<File> return the allChildFiles
	 */
	public ArrayList<File> getAllChildFiles() {
		return allChildFiles;
	}

	/**
	 * @param allChildFiles the allChildFiles to set
	 */
	public void setAllChildFiles(ArrayList<File> allChildFiles) {
		this.allChildFiles = allChildFiles;
	}

	/**
	 * @return String return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return char return the fileNumberSeperator
	 */
	public char getFileNumberSeperator() {
		return fileNumberSeperator;
	}

	/**
	 * @param fileNumberSeperator the fileNumberSeperator to set
	 */
	public void setFileNumberSeperator(char fileNumberSeperator) {
		this.fileNumberSeperator = fileNumberSeperator;
	}

	/**
	 * @return JList<String> return the listPanel
	 */
	public JList<String> getListPanel() {
		return listPanel;
	}

	/**
	 * @param listPanel the listPanel to set
	 */
	public void setListPanel(JList<String> listPanel) {
		this.listPanel = listPanel;
	}

	/**
	 * @return DefaultListModel<String> return the model
	 */
	public DefaultListModel<String> getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(DefaultListModel<String> model) {
		this.model = model;
	}

}
