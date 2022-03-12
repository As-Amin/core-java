package com.corejava.packages.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.commons.io.comparator.NameFileComparator;
import net.miginfocom.swing.MigLayout;

public class FileScrollView {
	private JPanel listPanel = new JPanel();
	private ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
	private ArrayList<JButton> allButtons = new ArrayList<JButton>();
	private ArrayList<String> allButtonNames = new ArrayList<String>();
	private ArrayList<File> allButtonsFiles = new ArrayList<File>();

	private char fileNumberSeperator;
	private String directory;

	public FileScrollView(String directory, char fileNumberSeperator) {
		this.directory = directory;
		this.fileNumberSeperator = fileNumberSeperator;
	}

	// Generates the scroll panel for which the topics will be displayed,
	// loads the topics onto the scrollpanel
	public JScrollPane Generate() {
		listPanel.setLayout(new MigLayout("wrap"));
		JScrollPane topicScrollPane = new JScrollPane(listPanel);
		topicScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		topicScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		this.generateLabels();
		return topicScrollPane;
	}

	private void generateLabels() {
		File[] files = new File(directory).listFiles();
		Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
		for (File section : files) {
			String sectionName = getFileName(section);
			JLabel label = new JLabel(sectionName);
			listPanel.add(label, "hmin 30, grow");
			allLabels.add(label);
			generateButtons(section);
		}
	}

	private void generateButtons(File sectionDir) {
		for (File file : sectionDir.listFiles()) {
			String fileName = getFileName(file);
			JButton button = new JButton(" " + fileName);
			listPanel.add(button, "hmin 30, grow");
			allButtons.add(button);
			allButtonNames.add(fileName);
			allButtonsFiles.add(file);
		}
	}

	private String getFileName(File file) {
		String topicAndFileType[] = file.getName().split("\\.", 2);
		String numberTopic[] = topicAndFileType[0].split("\\" + fileNumberSeperator + " ", 2);
		return numberTopic[1];
	}

	/**
	 * @return JPanel return the topicListPanel
	 */
	public JPanel getTopicListPanel() {
		return listPanel;
	}

	/**
	 * @param topicListPanel the topicListPanel to set
	 */
	public void setTopicListPanel(JPanel topicListPanel) {
		this.listPanel = topicListPanel;
	}

	/**
	 * @return ArrayList<JButton> return the allTopicButtons
	 */
	public ArrayList<JButton> getAllTopicButtons() {
		return allButtons;
	}

	/**
	 * @param allTopicButtons the allTopicButtons to set
	 */
	public void setAllTopicButtons(ArrayList<JButton> allTopicButtons) {
		this.allButtons = allTopicButtons;
	}

	/**
	 * @return JPanel return the listPanel
	 */
	public JPanel getListPanel() {
		return listPanel;
	}

	/**
	 * @param listPanel the listPanel to set
	 */
	public void setListPanel(JPanel listPanel) {
		this.listPanel = listPanel;
	}

	/**
	 * @return ArrayList<JButton> return the allButtons
	 */
	public ArrayList<JButton> getAllButtons() {
		return allButtons;
	}

	/**
	 * @param allButtons the allButtons to set
	 */
	public void setAllButtons(ArrayList<JButton> allButtons) {
		this.allButtons = allButtons;
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
	 * @return ArrayList<File> return the allButtonsFiles
	 */
	public ArrayList<File> getAllButtonsFiles() {
		return allButtonsFiles;
	}

	/**
	 * @param allButtonsFiles the allButtonsFiles to set
	 */
	public void setAllButtonsFiles(ArrayList<File> allButtonsFiles) {
		this.allButtonsFiles = allButtonsFiles;
	}

	/**
	 * @return ArrayList<String> return the allFileNames
	 */
	public ArrayList<String> getAllButtonNames() {
		return allButtonNames;
	}

	/**
	 * @param allFileNames the allFileNames to set
	 */
	public void setAllButtonNames(ArrayList<String> allFileNames) {
		this.allButtonNames = allFileNames;
	}

	/**
	 * @return ArrayList<JLabel> return the allLabels
	 */
	public ArrayList<JLabel> getAllLabels() {
		return allLabels;
	}

	/**
	 * @param allLabels the allLabels to set
	 */
	public void setAllLabels(ArrayList<JLabel> allLabels) {
		this.allLabels = allLabels;
	}
}
