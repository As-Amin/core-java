package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import com.corejava.packages.colors.Colors;
import com.corejava.packages.components.TitleBox;
import com.corejava.packages.components.FileScrollView;
import com.corejava.packages.text_pane_components.Text;
import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	private int panelWidth = 700;
	private int panelHeight = 500;
	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();
	// Right panel - public and global because needs to be modified by different
	// classes and functions outside of this one i.e. TopicList
	public static TitleBox topicTitleBox = new TitleBox("Select a topic!");
	public static TopicLearnArea topicLearnArea = new TopicLearnArea();
	// Left panel
	private FileScrollView topicList = new FileScrollView(Main.TOPICS_DIRECTORY, ')');

	public Home() throws BadLocationException, IOException {
		contentPane.setLayout(new MigLayout("", "[fill,30%!][fill,grow]", // width, column
				"[fill,grow]")); // height, row

		contentPane.add(topicList.Generate(), "cell 0 0");

		// Right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new MigLayout("", "[fill,grow]", // width, column
				"[fill,10%!][fill,grow]")); // height, row
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
		rightPanel.add(topicLearnArea.Generate(), "cell 0 1");
		contentPane.add(rightPanel, "cell 1 0 0 2");

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
		frame.setTitle(Main.APP_NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	private void setInitialLearnArea() throws BadLocationException, IOException {
		Text text = new Text(topicLearnArea.getTextPane(),
				"To begin, select a topic from the left side topics list!", Colors.WHITE.getColor(),
				false);
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

	/**
	 * @return TopicList return the topicList
	 */
	public FileScrollView getTopicList() {
		return topicList;
	}

	/**
	 * @param topicList the topicList to set
	 */
	public void setTopicList(FileScrollView topicList) {
		this.topicList = topicList;
	}

}
