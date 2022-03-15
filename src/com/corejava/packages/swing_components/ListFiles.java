package com.corejava.packages.swing_components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.corejava.packages.home.Home;
import org.apache.commons.io.comparator.NameFileComparator;

public class ListFiles {
	private JScrollPane scrollPane;
	private JList<String> listPanel;
	private ArrayList<String> allParentDir = new ArrayList<String>();
	private ArrayList<File> allFiles = new ArrayList<File>();

	private DefaultListModel<String> model = new DefaultListModel<>();

	private char fileNumberSeperator;
	private String directory;

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

	private void generateLabels() {
		File[] files = new File(directory).listFiles();
		Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
		for (File dir : files) {
			String sectionName = getFileName(dir);
			generateButtons(dir, sectionName);
		}
	}

	private void generateButtons(File sectionDir, String sectionName) {
		for (File file : sectionDir.listFiles()) {
			String fileName = getFileName(file);
			model.addElement(fileName);
			allParentDir.add(sectionName);
			allFiles.add(file);
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
					} catch (IOException e) {
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

	/**
	 * @return JScrollPane return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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
	 * @return ArrayList<String> return the allSections
	 */
	public ArrayList<String> getAllSections() {
		return allParentDir;
	}

	/**
	 * @param allSections the allSections to set
	 */
	public void setAllSections(ArrayList<String> allSections) {
		this.allParentDir = allSections;
	}

	/**
	 * @return ArrayList<File> return the allFiles
	 */
	public ArrayList<File> getAllFiles() {
		return allFiles;
	}

	/**
	 * @param allFiles the allFiles to set
	 */
	public void setAllFiles(ArrayList<File> allFiles) {
		this.allFiles = allFiles;
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

}
