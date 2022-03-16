package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import com.corejava.packages.Main;
import com.corejava.packages.textpane_ui.Text;
import com.corejava.packages.ui.Button;
import com.corejava.packages.ui.LearnArea;
import com.corejava.packages.ui.LineSeperator;
import com.corejava.packages.ui.ListFiles;
import com.corejava.packages.ui.MenuBar;
import com.corejava.packages.ui.TextBox;
import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	private int frameWidth = 750;
	private int frameHeight = 550;

	private Container contentPane = this.getContentPane();

	// Left panel
	private TextBox searchTopicBox = new TextBox("", true);
	private Button searchButton = new Button("Search");
	private LineSeperator searchButtonSeperator = new LineSeperator(SwingConstants.HORIZONTAL);
	private ListFiles topicList = new ListFiles(Main.TOPICS_DIRECTORY, ')');

	// Right panel - public and global because needs to be modified by different
	// classes and functions outside of this one i.e. TopicList
	public static TextBox topicTitleBox = new TextBox("", false);
	public static TextBox sectionTitleBox = new TextBox("", false);
	public static LearnArea topicLearnArea = new LearnArea();
	public static TextBox topicFeedbackArea = new TextBox("", false);

	private MenuBar menuBar = new MenuBar();

	public Home() throws BadLocationException, IOException {
		// Topic list panel is fixed according to panel width
		contentPane.setLayout(
				new MigLayout("", "[fill," + frameWidth * 0.25 + "!][fill,grow]", "[fill,grow]"));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new MigLayout("", "0[fill,grow]0",
				"0[][][fill, " + frameHeight * 0.01 + "!][fill,grow]0")); // height, row
		leftPanel.add(searchTopicBox.Generate(), "cell 0 0");
		leftPanel.add(searchButton.Generate(), "cell 0 1");
		leftPanel.add(searchButtonSeperator.Generate(), "cell 0 2");
		leftPanel.add(topicList.Generate(), "cell 0 3");
		contentPane.add(leftPanel, "cell 0 0");

		// Right panel
		JPanel rightPanel = new JPanel();
		// Title box is fixed according to panel height
		rightPanel.setLayout(new MigLayout("", "0[fill,grow]0", // width, column
				"0[][][fill,grow][]0"));
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
		rightPanel.add(sectionTitleBox.Generate(), "cell 0 1");
		rightPanel.add(topicLearnArea.Generate(), "cell 0 2");
		rightPanel.add(topicFeedbackArea.Generate(), "cell 0 3");
		contentPane.add(rightPanel, "cell 1 0");

		this.setJMenuBar(menuBar.Generate());

		// Client properties
		searchTopicBox.getTextField().putClientProperty("JTextField.placeholderText", "Search");
		topicTitleBox.getTextField().putClientProperty("FlatLaf.style",
				"font: $large.font;" + "foreground: @accentColor;");
		topicFeedbackArea.getTextField().putClientProperty("FlatLaf.style",
				"foreground: @accentColor;");

		searchTopicBox.getTextField().setToolTipText("Search topics");
		topicList.getListPanel().setToolTipText("List of all topics");
		topicTitleBox.getTextField().setToolTipText("Title of the topic");
		sectionTitleBox.getTextField().setToolTipText("Section of the topic");
		topicFeedbackArea.getTextField().setToolTipText("Feedback for this topic");

		setupFrame();
		setInitialLearnArea();
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		this.pack();
		this.setLocation(1000, 300);
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	private void setInitialLearnArea() throws BadLocationException, IOException {
		topicTitleBox.setText("Topic: Start learning");
		sectionTitleBox.setText("Section: Home");
		topicFeedbackArea.setText("Feedback: Select a topic from the list!");
		Text heading = new Text("Get started", Main.SECONDARY_ACCENT_COLOR, false,
				topicLearnArea.getTextPane());
		heading.Generate();
		Text paragraph = new Text(
				"To begin, select a topic from the left side topics list! You can use the arrow keys or your cursor. Hover over any part of the screen to see what each section is for.",
				null, false, topicLearnArea.getTextPane());
		paragraph.Generate();
	}

	/**
	 * @return int return the frameWidth
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @param frameWidth the frameWidth to set
	 */
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	/**
	 * @return int return the frameHeight
	 */
	public int getFrameHeight() {
		return frameHeight;
	}

	/**
	 * @param frameHeight the frameHeight to set
	 */
	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	/**
	 * @param contentPane the contentPane to set
	 */
	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

	/**
	 * @return TextBox return the searchTopicBox
	 */
	public TextBox getSearchTopicBox() {
		return searchTopicBox;
	}

	/**
	 * @param searchTopicBox the searchTopicBox to set
	 */
	public void setSearchTopicBox(TextBox searchTopicBox) {
		this.searchTopicBox = searchTopicBox;
	}

	/**
	 * @return Button return the searchButton
	 */
	public Button getSearchButton() {
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	/**
	 * @return ListFiles return the topicList
	 */
	public ListFiles getTopicList() {
		return topicList;
	}

	/**
	 * @param topicList the topicList to set
	 */
	public void setTopicList(ListFiles topicList) {
		this.topicList = topicList;
	}

	/**
	 * @param menuBar the menuBar to set
	 */
	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

}
