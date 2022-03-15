package com.corejava.packages.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.comparator.NameFileComparator;

import com.corejava.packages.home.Home;

public class ListFiles {
	private ArrayList<File> allFiles = new ArrayList<File>();
	private ArrayList<String> allParentDir = new ArrayList<String>();
	private String directory;
	private char fileNumberSeperator;

	private JList<String> listPanel;

	private DefaultListModel<String> model = new DefaultListModel<>();
	private JScrollPane scrollPane;

	public ListFiles(String directory, char fileNumberSeperator) {
		this.directory = directory;
		this.fileNumberSeperator = fileNumberSeperator;
	}

	public JScrollPane Generate() {
		this.generateLabels();
		this.generateListeners();
		scrollPane = new JScrollPane(listPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);
		return scrollPane;
	}

	private void generateButtons(File sectionDir, String sectionName) {
		for (File file : sectionDir.listFiles()) {
			String fileName = getFileName(file);
			model.addElement(fileName);
			allParentDir.add(sectionName);
			allFiles.add(file);
		}
	}

	private void generateLabels() {
		File[] files = new File(directory).listFiles();
		Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
		for (File dir : files) {
			String sectionName = getFileName(dir);
			generateButtons(dir, sectionName);
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
						Home.topicLearnArea.OpenFile(allFiles.get(listPanel.getSelectedIndex()));
						Home.listTitleBox.setText(allParentDir.get(listPanel.getSelectedIndex()));
					} catch (IOException | ConfigurationException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * @return ArrayList<File> return the allFiles
	 */
	public ArrayList<File> getAllFiles() {
		return allFiles;
	}

	/**
	 * @return ArrayList<String> return the allSections
	 */
	public ArrayList<String> getAllSections() {
		return allParentDir;
	}

	/**
	 * @return String return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	private String getFileName(File file) {
		String topicAndFileType[] = file.getName().split("\\.", 2);
		String numberTopic[] = topicAndFileType[0].split("\\" + fileNumberSeperator + " ", 2);
		return numberTopic[1];
	}

	/**
	 * @return char return the fileNumberSeperator
	 */
	public char getFileNumberSeperator() {
		return fileNumberSeperator;
	}

	/**
	 * @return JList<String> return the listPanel
	 */
	public JList<String> getListPanel() {
		return listPanel;
	}

	/**
	 * @return DefaultListModel<String> return the model
	 */
	public DefaultListModel<String> getModel() {
		return model;
	}

	/**
	 * @return JScrollPane return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param allFiles the allFiles to set
	 */
	public void setAllFiles(ArrayList<File> allFiles) {
		this.allFiles = allFiles;
	}

	/**
	 * @param allSections the allSections to set
	 */
	public void setAllSections(ArrayList<String> allSections) {
		this.allParentDir = allSections;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @param fileNumberSeperator the fileNumberSeperator to set
	 */
	public void setFileNumberSeperator(char fileNumberSeperator) {
		this.fileNumberSeperator = fileNumberSeperator;
	}

	/**
	 * @param listPanel the listPanel to set
	 */
	public void setListPanel(JList<String> listPanel) {
		this.listPanel = listPanel;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(DefaultListModel<String> model) {
		this.model = model;
	}

	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

}
