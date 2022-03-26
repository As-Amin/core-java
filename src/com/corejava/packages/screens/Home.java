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
import com.corejava.packages.textpane.Text;
import com.corejava.packages.ui.LearnArea;
import com.corejava.packages.ui.ListFiles;
import com.corejava.packages.ui.MenuBar;
import com.corejava.packages.ui.ScrollTextPane;
import com.corejava.packages.ui.TextBox;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	// The frame dimensions (the initial size of the window)
	private int frameWidth = 800;
	private int frameHeight = 600;

	// The content pane derived from the classes frame - used to
	// add components created to
	private Container contentPane = this.getContentPane();

	/////////////////////////////////////////////////////////////////////////////
	// Left panel components - Initialise the components that will
	// be displayed on the left side of the screen
	private JPanel leftPanel = new JPanel();
	private double leftPanelWidth = 200;

	private JPanel searchPanel = new JPanel();
	private TextBox searchTopicInput = new TextBox("", "", true);
	private JButton searchButton = new JButton("Search");
	private JButton resetButton = new JButton("Reset");

	private JPanel topicListPanel = new JPanel();
	private ListFiles topicList = new ListFiles(Main.TOPICS_DIRECTORY, ')');

	private JPanel topicFeedbackPanel = new JPanel();
	public static ScrollTextPane topicFeedbackArea = new ScrollTextPane();
	/////////////////////////////////////////////////////////////////////////////

	// Right panel components - Public and global because needs to be
	// modified by different classes and functions outside of this one
	private JPanel rightPanel = new JPanel();
	public static TextBox topicTitleBox = new TextBox("Topic: ", "", false);
	public static TextBox sectionTitleBox = new TextBox("Section: ", "", false);
	public static LearnArea topicLearnArea = new LearnArea();

	// The menu bar at the top of the window - is not apart of the content pane but frame
	private MenuBar menuBar = new MenuBar(true);

	public Home() {
		// Topic list panel is fixed according to panel width
		contentPane.setLayout(
				new MigLayout("", "[fill," + leftPanelWidth + "!][fill,grow]", "[fill,grow]"));

		/////////////////////////////////////////////////////////////////////////////
		// LEFT PANEL - Has its own layout manager so doesnt interfere with right panel
		// components and easier to manage
		leftPanel.setLayout(new MigLayout("", "0[fill,grow]0", // width, height
				"0[][fill,grow][fill, " + frameHeight * 0.15 + "!]0")); // height
		searchPanel.setLayout(
				new MigLayout("", "0[fill,grow][fill,grow]0", "0[fill,grow][fill,grow]0"));
		searchPanel.add(searchTopicInput, "cell 0 0 2 0");
		searchPanel.add(searchButton, "cell 0 1");
		searchPanel.add(resetButton, "cell 1 1");
		leftPanel.add(searchPanel, "cell 0 0");

		topicListPanel.setLayout(new MigLayout("", "0[fill,grow]0", "0[fill,grow]0"));
		topicListPanel.add(topicList.generate(), "cell 0 0");
		leftPanel.add(topicListPanel, "cell 0 1");

		topicFeedbackPanel.setLayout(new MigLayout("", "0[fill,grow]0", "0[fill,grow]0"));
		topicFeedbackPanel.add(topicFeedbackArea.generate(), "cell 0 0");
		leftPanel.add(topicFeedbackPanel, "cell 0 2");

		contentPane.add(leftPanel, "cell 0 0");
		/////////////////////////////////////////////////////////////////////////////

		// Right panel - Just like left panel but different components
		rightPanel.setLayout(new MigLayout("", "0[fill,grow]0", // Width, column
				"0[][][fill,grow]0")); // Height, row
		rightPanel.add(topicTitleBox, "cell 0 0");
		rightPanel.add(sectionTitleBox, "cell 0 1");
		rightPanel.add(topicLearnArea.generate(), "cell 0 2");
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
		searchPanel.putClientProperty(FlatClientProperties.STYLE_CLASS, "myRoundPanel");
		topicListPanel.putClientProperty(FlatClientProperties.STYLE_CLASS, "myRoundPanel");
		topicFeedbackPanel.putClientProperty(FlatClientProperties.STYLE_CLASS, "myRoundPanel");
		searchTopicInput.putClientProperty("JTextField.placeholderText", "Search topics...");
		topicTitleBox.putClientProperty("FlatLaf.style",
				"font: $large.font;" + "foreground: @accentColor;");
		topicFeedbackArea.putClientProperty("FlatLaf.style",
				"background: @componentBackground;" + "foreground: @secondaryAccentColor");
	}

	/**
	 * Configure and set the tool tips for components in the content pane
	 */
	private void setToolTips() {
		searchTopicInput.setToolTipText("Search");
		topicList.getListPanel().setToolTipText("List of all topics");
		topicTitleBox.setToolTipText("Title of the topic");
		sectionTitleBox.setToolTipText("Section of the topic");
		topicFeedbackArea.setToolTipText("Feedback for this topic");
	}

	/**
	 * Set the properties of the learn area (right panel). This is so the user sees some
	 * instructions as soon as they open the app.
	 */
	private void setInitialLearnArea() {
		topicTitleBox.addToStartingText("Not selected");
		sectionTitleBox.addToStartingText("Not selected");
		topicFeedbackArea.setText("When you answer a question, feedback will appear here!");
		Text heading =
				new Text("Get started", Main.SECONDARY_ACCENT_COLOR, topicLearnArea.getTextPane());
		heading.generateText();
		Text paragraph = new Text(
				"To begin, select a topic from the left side topics list! You can use the arrow keys or your cursor. Hover over any part of the screen to see what each section is for.",
				null, topicLearnArea.getTextPane());
		paragraph.generateText();
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
}