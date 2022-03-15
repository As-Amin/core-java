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
import com.corejava.packages.ui.LearnArea;
import com.corejava.packages.ui.LineSeperator;
import com.corejava.packages.ui.ListFiles;
import com.corejava.packages.ui.MenuBar;
import com.corejava.packages.ui.TextBox;

import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	private int frameWidth = 750;
	private int frameHeight = 550;

	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();

	// Left panel
	public static TextBox listTitleBox = new TextBox("Topics");
	private LineSeperator listTitleSeperator = new LineSeperator(SwingConstants.HORIZONTAL);
	private ListFiles topicList = new ListFiles(Main.TOPICS_DIRECTORY, ')');

	// Right panel - public and global because needs to be modified by different
	// classes and functions outside of this one i.e. TopicList
	public static TextBox topicTitleBox = new TextBox("Select a topic!");
	private LineSeperator topicTitleSeperator = new LineSeperator(SwingConstants.HORIZONTAL);
	public static LearnArea topicLearnArea = new LearnArea();

	private MenuBar menuBar = new MenuBar(frame);

	public Home() throws BadLocationException, IOException {
		// Topic list panel is fixed according to panel width
		contentPane.setLayout(new MigLayout("", "0[fill," + frameWidth * 0.27 + "!]0[fill,grow]0",
				"0[fill,grow]0"));

		JPanel leftPanel = new JPanel();
		// Title box is fixed according to panel height
		leftPanel.setLayout(new MigLayout("", "[fill,grow]",
				"[][fill, " + frameHeight * 0.01 + "!][fill,grow]")); // height, row
		leftPanel.add(listTitleBox.Generate(), "cell 0 0");
		leftPanel.add(listTitleSeperator.Generate(), "cell 0 1");
		leftPanel.add(topicList.Generate(), "cell 0 2");
		contentPane.add(leftPanel, "cell 0 0");

		// Right panel
		JPanel rightPanel = new JPanel();
		// Title box is fixed according to panel height
		rightPanel.setLayout(new MigLayout("", "[fill,grow]", // width, column
				"[][fill, " + frameHeight * 0.01 + "!][fill,grow]"));
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
		rightPanel.add(topicTitleSeperator.Generate(), "cell 0 1");
		rightPanel.add(topicLearnArea.Generate(), "cell 0 2");
		contentPane.add(rightPanel, "cell 1 0");

		menuBar.Generate();

		setupFrame();
		setInitialLearnArea();
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		frame.pack();
		frame.setLocation(1000, 300);
		frame.setMinimumSize(new Dimension(frameWidth, frameHeight));
		frame.setSize(new Dimension(frameWidth, frameHeight));
		frame.setResizable(true);
		frame.setVisible(true);
		// frame.setTitle(Main.APP_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	private void setInitialLearnArea() throws BadLocationException, IOException {
		Text text = new Text("To begin, select a topic from the left side topics list!", null,
				false, topicLearnArea.getTextPane());
		text.Generate();
	}

	/**
	 * @return JFrame return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return int return the panelHeight
	 */
	public int getPanelHeight() {
		return frameHeight;
	}

	/**
	 * @return int return the panelWidth
	 */
	public int getPanelWidth() {
		return frameWidth;
	}

	/**
	 * @param contentPane the contentPane to set
	 */
	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @param panelHeight the panelHeight to set
	 */
	public void setPanelHeight(int panelHeight) {
		this.frameHeight = panelHeight;
	}

	/**
	 * @param panelWidth the panelWidth to set
	 */
	public void setPanelWidth(int panelWidth) {
		this.frameWidth = panelWidth;
	}
}
