package com.corejava.packages.screens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.corejava.packages.Main;
import com.corejava.packages.textpane.PlainText;
import com.corejava.packages.ui.LearnArea;
import com.corejava.packages.ui.ListFiles;
import com.corejava.packages.ui.MenuBar;
import com.corejava.packages.ui.ScrollTextPane;
import com.corejava.packages.ui.TextBox;

import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	// The frame dimensions (the initial size of the window)
	private int frameWidth = 800;
	private int frameHeight = 550;

	// The content pane derived from the classes frame - used to
	// add components created to
	private Container contentPane = this.getContentPane();

	// Left panel components - Initialise the components that will
	// be displayed on the left side of the screen
	private JPanel leftPanel = new JPanel();
	private double leftPanelWidth = 220;
	private JPanel searchButtonPanel = new JPanel();
	private TextBox searchTopicInput = new TextBox("", true);
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");
	private JSeparator buttonSeperator = new JSeparator(SwingConstants.HORIZONTAL);
	private ListFiles topicList = new ListFiles(Main.TOPICS_DIRECTORY, ')');
	private JSeparator feedbackSeperator = new JSeparator(SwingConstants.HORIZONTAL);
	public static ScrollTextPane topicFeedbackArea = new ScrollTextPane();

	// Right panel components - Public and global because needs to be
	// modified by different classes and functions outside of this one
	private JPanel rightPanel = new JPanel();
	public static TextBox topicTitleBox = new TextBox("", false);
	private JSeparator titleSeperator = new JSeparator(SwingConstants.HORIZONTAL);
	public static LearnArea topicLearnArea = new LearnArea();
	private JSeparator sectionSeperator = new JSeparator(SwingConstants.HORIZONTAL);
	public static TextBox sectionTitleBox = new TextBox("", false);

	// The menu bar at the top of the window - is not apart of the content pane but frame
	private MenuBar menuBar = new MenuBar();

	public Home() {
		// Topic list panel is fixed according to panel width
		contentPane.setLayout(
				new MigLayout("", "0[fill," + leftPanelWidth + "!]0[fill,grow]0", "0[fill,grow]0"));

		// Left panel - Has its own layout manager so doesnt interfere with right panel
		// components and easier to manage
		leftPanel.setLayout(new MigLayout("", "[fill,grow]", // width, height
				"[]10[]10[fill, " + frameHeight * 0.01 + "!][fill,grow]10[fill, "
						+ frameHeight * 0.01 + "!][fill, " + frameHeight * 0.15 + "!]")); // height
		leftPanel.add(searchTopicInput, "cell 0 0");
		searchButtonPanel.setLayout(new MigLayout("", "0[fill,grow][fill,grow]0", "0[]0"));
		searchButtonPanel.add(searchButton, "cell 0 0");
		searchButtonPanel.add(resetButton, "cell 1 0");
		leftPanel.add(searchButtonPanel, "cell 0 1");
		leftPanel.add(buttonSeperator, "cell 0 2");
		leftPanel.add(topicList, "cell 0 3");
		leftPanel.add(feedbackSeperator, "cell 0 4");
		leftPanel.add(topicFeedbackArea, "cell 0 5");
		contentPane.add(leftPanel, "cell 0 0");

		// Right panel - Just like left panel but different components
		rightPanel.setLayout(new MigLayout("", "[fill,grow]", // Width, column
				"[]10[fill, " + frameHeight * 0.01 + "!][fill,grow]10[fill, " + frameHeight * 0.01
						+ "!][]")); // Height, row
		rightPanel.add(topicTitleBox, "cell 0 0");
		rightPanel.add(titleSeperator, "cell 0 1");
		rightPanel.add(topicLearnArea, "cell 0 2");
		rightPanel.add(sectionSeperator, "cell 0 3");
		rightPanel.add(sectionTitleBox, "cell 0 4");
		contentPane.add(rightPanel, "cell 1 0");

		// Add the menu bar, by calling the function in the class which creates
		// and configures the menu bar.
		this.setJMenuBar(menuBar.generate());

		setupFrame(); // Setup the frames properties
		addButtonListeners();
		setClientProperties(); // Setup the client theming properties for FlatLaf
		setToolTips(); // Setup the tooltips for all component objects created
		setInitialLearnArea(); // Setup the text and components content in the learn area
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		this.pack();
		this.setLocation(1000, 300);
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	/**
	 * Configure custom client properties for theming (FLatLaf). These are unique values and so are
	 * not added to properties files
	 */
	private void setClientProperties() {
		searchTopicInput.putClientProperty("JTextField.placeholderText", "Search topics...");
		searchTopicInput.putClientProperty("FlatLaf.style", "background: @background");
		topicTitleBox.putClientProperty("FlatLaf.style", "font: $large.font;");
		topicFeedbackArea.getTextPane().putClientProperty("FlatLaf.style",
				"background: @componentBackground;" + "foreground: @accentColor;");

		leftPanel.putClientProperty("FlatLaf.style", "background: @componentBackground");
		searchButtonPanel.putClientProperty("FlatLaf.style", "background: @componentBackground");
	}

	/**
	 * Configure and set the tool tips for components in the content pane
	 */
	private void setToolTips() {
		searchTopicInput.setToolTipText("Search");
		topicList.getListPanel().setToolTipText("List of all topics");
		topicTitleBox.setToolTipText("Title of the topic");
		sectionTitleBox.setToolTipText("Section of the topic");
		topicFeedbackArea.getTextPane().setToolTipText("Feedback for this topic");
	}

	/**
	 * Set the properties of the learn area (right panel). This is so the user sees some
	 * instructions as soon as they open the app.
	 */
	private void setInitialLearnArea() {
		topicTitleBox.setText("No topic selected");
		sectionTitleBox.setText("No section selected");
		topicFeedbackArea.setText("When you answer a question, feedback will appear here!");
		PlainText paragraph = new PlainText(
				"To begin, select a topic from the left side topics list! You can use the arrow keys or your cursor."
						+ " Hover over any part of the screen to see what each section is for.",
				null, topicLearnArea.getTextPane());
	}

	private void addButtonListeners() {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				topicList.filter(searchTopicInput.getText());
				topicLearnArea.clearAll();
				setInitialLearnArea();
			}
		});
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				searchTopicInput.clearAll();
				topicList.reset();
				topicLearnArea.clearAll();
				setInitialLearnArea();
			}
		});
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
	@Override
	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

	/**
	 * @return TextBox return the searchTopicBox
	 */
	public TextBox getSearchTopicBox() {
		return searchTopicInput;
	}

	/**
	 * @param searchTopicBox the searchTopicBox to set
	 */
	public void setSearchTopicBox(TextBox searchTopicBox) {
		this.searchTopicInput = searchTopicBox;
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

	/**
	 * @return JPanel return the leftPanel
	 */
	public JPanel getLeftPanel() {
		return leftPanel;
	}

	/**
	 * @param leftPanel the leftPanel to set
	 */
	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	/**
	 * @return double return the leftPanelWidth
	 */
	public double getLeftPanelWidth() {
		return leftPanelWidth;
	}

	/**
	 * @param leftPanelWidth the leftPanelWidth to set
	 */
	public void setLeftPanelWidth(double leftPanelWidth) {
		this.leftPanelWidth = leftPanelWidth;
	}

	/**
	 * @return JPanel return the rightPanel
	 */
	public JPanel getRightPanel() {
		return rightPanel;
	}

	/**
	 * @param rightPanel the rightPanel to set
	 */
	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	/**
	 * @return JPanel return the searchButtonPanel
	 */
	public JPanel getSearchButtonPanel() {
		return searchButtonPanel;
	}

	/**
	 * @param searchButtonPanel the searchButtonPanel to set
	 */
	public void setSearchButtonPanel(JPanel searchButtonPanel) {
		this.searchButtonPanel = searchButtonPanel;
	}

	/**
	 * @return TextBox return the searchTopicInput
	 */
	public TextBox getSearchTopicInput() {
		return searchTopicInput;
	}

	/**
	 * @param searchTopicInput the searchTopicInput to set
	 */
	public void setSearchTopicInput(TextBox searchTopicInput) {
		this.searchTopicInput = searchTopicInput;
	}

	/**
	 * @return JButton return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}

	/**
	 * @return JButton return the resetButton
	 */
	public JButton getResetButton() {
		return resetButton;
	}

	/**
	 * @param resetButton the resetButton to set
	 */
	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}

	/**
	 * @return JSeparator return the buttonSeperator
	 */
	public JSeparator getButtonSeperator() {
		return buttonSeperator;
	}

	/**
	 * @param buttonSeperator the buttonSeperator to set
	 */
	public void setButtonSeperator(JSeparator buttonSeperator) {
		this.buttonSeperator = buttonSeperator;
	}

	/**
	 * @return JSeparator return the feedbackSeperator
	 */
	public JSeparator getFeedbackSeperator() {
		return feedbackSeperator;
	}

	/**
	 * @param feedbackSeperator the feedbackSeperator to set
	 */
	public void setFeedbackSeperator(JSeparator feedbackSeperator) {
		this.feedbackSeperator = feedbackSeperator;
	}

	/**
	 * @return JSeparator return the titleSeperator
	 */
	public JSeparator getTitleSeperator() {
		return titleSeperator;
	}

	/**
	 * @param titleSeperator the titleSeperator to set
	 */
	public void setTitleSeperator(JSeparator titleSeperator) {
		this.titleSeperator = titleSeperator;
	}

	/**
	 * @return JSeparator return the sectionSeperator
	 */
	public JSeparator getSectionSeperator() {
		return sectionSeperator;
	}

	/**
	 * @param sectionSeperator the sectionSeperator to set
	 */
	public void setSectionSeperator(JSeparator sectionSeperator) {
		this.sectionSeperator = sectionSeperator;
	}
}
