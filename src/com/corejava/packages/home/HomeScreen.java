package com.corejava.packages.home;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.corejava.packages.fonts.FN;
import com.corejava.packages.fonts.FS;

import net.miginfocom.swing.MigLayout;

public class HomeScreen extends JFrame{
	private int panelWidth = 750;
	private int panelHeight = 550;

	private JFrame frame = this;
	private Container contentPane = frame.getContentPane();

	private ArrayList<JButton> allTopicButtons = new ArrayList<JButton>();

	public HomeScreen() {
		contentPane.setLayout(
				new MigLayout(
						"",
						"[fill,30%!][fill,grow][fill,5%!]", // width, column
						"[][fill,grow]")); // height, row
		
		LeftPanel leftPanel = new LeftPanel();
		leftPanel.GetLeftPanel().setLayout(
				new MigLayout("wrap", "[fill,grow]"));
		
		RightPanel rightPanel = new RightPanel();
		rightPanel.GetRightPanel().setLayout(
				new MigLayout("", "[fill,grow]", "[fill,10%!][fill,grow]"));
		
		
		contentPane.add(leftPanel.GenerateTopicScroll(), "cell 0 1");
	    contentPane.add(leftPanel.GenerateLogo(), "cell 0 2");
	    
	    rightPanel.GetRightPanel().add(rightPanel.GenerateTitleBox(), "gapx 5, cell 0 0");
	    rightPanel.GetRightPanel().add(rightPanel.GenerateTextArea(), "cell 0 1");
	    contentPane.add(rightPanel.GetRightPanel(), "cell 1 0 2 3, grow");
		
		setupFrame();
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		frame.pack();
		frame.setLocation(1000, 300);
		frame.setMinimumSize(new Dimension(panelWidth, panelHeight));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}
}
