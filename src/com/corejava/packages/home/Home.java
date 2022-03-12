package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import com.corejava.packages.colors.Colors;
import com.corejava.packages.components.Logo;
import com.corejava.packages.components.TitleBox;
import com.corejava.packages.components.FileScrollView;
import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;
import com.corejava.packages.text_pane_components.Text;
import net.miginfocom.swing.MigLayout;

public class Home extends JFrame {
	private int panelWidth = 700;
	private int panelHeight = 500;
	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();
	// Right panel - public and global because needs to be modified by different
	// classes and functions outside of this one i.e. TopicList
	private TitleBox topicTitleBox = new TitleBox("Select a topic!", null, Colors.THEME.getColor(),
			new Font(FN.CONSOLAS.getFN(), Font.BOLD, FS.TOPIC_TITLE.getFS()));
	private TopicLearnArea topicLearnArea = new TopicLearnArea();
	// Left panel
	private Logo logo = new Logo(FS.SIDE_LOGO.getFS());
	private FileScrollView topicList = new FileScrollView(Main.TOPICS_DIRECTORY, ')');

	private int buttonIndex = 0;

	public Home() throws BadLocationException, IOException {
		contentPane.setLayout(new MigLayout("", "[fill,30%!][fill,grow]", // width, column
				"[fill,grow]")); // height, row

		// Left panel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new MigLayout("", "[fill,30%!]", // width, column
				"[fill,grow][fill,10%!]")); // height, row
		contentPane.add(topicList.Generate(), "cell 0 0");
		contentPane.add(logo.Generate(), "cell 0 1");
		contentPane.add(leftPanel, "cell 0 1 0 0");

		// Right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new MigLayout("", "[fill,grow]", // width, column
				"[fill,10%!][fill,grow]")); // height, row
		rightPanel.add(topicTitleBox.Generate(), "cell 0 0");
		rightPanel.add(topicLearnArea.Generate(), "cell 0 1");
		contentPane.add(rightPanel, "cell 1 0 0 2");

		setupFrame();
		configureTopicListLabels();
		configureTopicListButtons();
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	private void configureTopicListButtons() {
		ArrayList<JButton> allButtons = topicList.getAllButtons();
		for (JButton topicButton : allButtons) {
			topicButton.setHorizontalAlignment(SwingConstants.LEFT);
			topicButton.setBorder(null);
			topicButton.setForeground(Colors.FADED_WHITE.getColor());
			topicButton.setBackground(null);
			topicButton.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.SIDE_TEXT.getFS()));
			topicButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						for (JButton button : allButtons) {
							button.setForeground(Colors.FADED_WHITE.getColor());
							button.setBorder(null);
						}
						topicButton.setForeground(null);
						topicButton.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0,
								Colors.THEME.getColor()));
						topicTitleBox.SetTitleBox(topicList.getAllButtonNames().get(buttonIndex));
						topicLearnArea.OpenFile(topicList.getAllButtonsFiles().get(buttonIndex));
						buttonIndex = buttonIndex + 1;
					} catch (IOException IOE) {
						IOE.printStackTrace();
					}
				}
			});
		}
	}

	private void configureTopicListLabels() {
		ArrayList<JLabel> allLabels = topicList.getAllLabels();
		for (JLabel label : allLabels) {
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setForeground(Colors.THEME_SECONDARY.getColor());
			label.setFont(new Font(FN.NOTO.getFN(), Font.BOLD, FS.SIDE_HEADING.getFS()));
		}
	}

	private void setInitialLearnArea() throws BadLocationException, IOException {
		Text text = new Text(topicLearnArea.getTextPane(),
				"To begin, select a topic from the left side topics list!", Colors.WHITE.getColor(),
				FS.TOPIC_TEXT.getFS(), FN.NOTO.getFN(), false);
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
	 * @return Logo return the logo
	 */
	public Logo getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(Logo logo) {
		this.logo = logo;
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
