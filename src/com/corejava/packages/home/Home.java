package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import com.corejava.packages.swing_components.LearnArea;
import com.corejava.packages.swing_components.LineSeperator;
import com.corejava.packages.swing_components.ListFiles;
import com.corejava.packages.swing_components.MenuBar;
import com.corejava.packages.swing_components.TextBox;
import com.corejava.packages.textpane_components.Text;
import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	private int panelWidth = 700;
	private int panelHeight = 500;
	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();

	private MenuBar menuBar = new MenuBar(frame);

	// Right panel - public and global because needs to be modified by different
	// classes and functions outside of this one i.e. TopicList
	public static TextBox topicTitleBox = new TextBox("Select a topic!");
	private LineSeperator topicTitleSeperator = new LineSeperator(SwingConstants.HORIZONTAL);
	public static LearnArea topicLearnArea = new LearnArea();

	// Left panel
	private TextBox listTitleBox = new TextBox("Topics");
	private LineSeperator listTitleSeperator = new LineSeperator(SwingConstants.HORIZONTAL);
	private ListFiles topicList = new ListFiles(Main.TOPICS_DIRECTORY, ')');

	public Home() throws BadLocationException, IOException {
		// Topic list panel is fixed according to panel width
		contentPane.setLayout(
				new MigLayout("", "0[fill," + panelWidth * 0.3 + "!]0[fill,grow]", "[fill,grow]"));

		JPanel leftPanel = new JPanel();
		// Title box is fixed according to panel height
		leftPanel.setLayout(new MigLayout("", "[fill,grow]",
				"[][fill, " + panelHeight * 0.01 + "!][fill,grow]")); // height, row
		leftPanel.add(listTitleBox.Generate(), "cell 0 0");
		leftPanel.add(listTitleSeperator.Generate(), "cell 0 1");
		leftPanel.add(topicList.Generate(), "cell 0 2");
		contentPane.add(leftPanel, "cell 0 0");

		// Right panel
		JPanel rightPanel = new JPanel();
		// Title box is fixed according to panel height
		rightPanel.setLayout(new MigLayout("", "[fill,grow]", // width, column
				"[][fill, " + panelHeight * 0.01 + "!][fill,grow]"));
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
		rightPanel.add(topicTitleSeperator.Generate(), "cell 0 1");
		rightPanel.add(topicLearnArea.Generate(), "cell 0 2");
		contentPane.add(rightPanel, "cell 1 0");

		menuBar.Generate();

		// Client properties for LAF
		rightPanel.putClientProperty("FlatLaf.style", "background: @backgroundSecondary");

		setupFrame();
		setInitialLearnArea();
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		frame.pack();
		frame.setLocation(1000, 300);
		frame.setMinimumSize(new Dimension(panelWidth, panelHeight));
		frame.setSize(new Dimension(panelWidth, panelHeight));
		frame.setResizable(true);
		frame.setVisible(true);
		// frame.setTitle(Main.APP_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	private void setInitialLearnArea() throws BadLocationException, IOException {
		Text text = new Text(topicLearnArea.getTextPane(),
				"To begin, select a topic from the left side topics list!");
		text.Generate();
	}

	/**
	 * @return int return the panelWidth
	 */
	public int getPanelWidth() {
		return panelWidth;
	}

	/**
	 * @param panelWidth the panelWidth to set
	 */
	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

	/**
	 * @return int return the panelHeight
	 */
	public int getPanelHeight() {
		return panelHeight;
	}

	/**
	 * @param panelHeight the panelHeight to set
	 */
	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}

	/**
	 * @return JFrame return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * @param contentPane the contentPane to set
	 */
	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

}
