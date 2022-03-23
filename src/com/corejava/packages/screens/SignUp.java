package com.corejava.packages.screens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import com.corejava.packages.ui.MenuBar;
import com.corejava.packages.ui.TextBox;

import net.miginfocom.swing.MigLayout;

public class SignUp extends JFrame {
	// The frame dimensions (the initial size of the window)
	private int frameWidth = 350;
	private int frameHeight = 550;

	// The content pane derived from the classes frame - used to
	// add components created to
	private Container contentPane = this.getContentPane();

	// Top panel components - Initialise the components that will
	// be displayed on the top side of the screen
	private JPanel centerPanel = new JPanel();
	private JLabel signUpText = new JLabel("Sign up");
	private TextBox usernameInput = new TextBox("", "", true);
	private JPasswordField passwordInput = new JPasswordField();
	private JPasswordField passwordInputConfirm = new JPasswordField();
	private JSeparator buttonSeperator = new JSeparator(SwingConstants.HORIZONTAL);
	private JButton signUpButton = new JButton("Sign up");
	private JButton backToLoginButton = new JButton("Back to login");

	private MenuBar menuBar = new MenuBar(false);

	public SignUp() {
		contentPane.setLayout(new MigLayout("", "[fill,grow]", "[fill,grow]"));

		// Center panel - Has its own layout manager so doesnt interfere with other panels making it
		// easier to manage
		centerPanel.setLayout(new MigLayout("", "0[fill,grow]0", // width
				"0[]30[][][][fill, " + frameHeight * 0.01 + "!][][]0")); // height
		centerPanel.add(signUpText, "cell 0 0");
		centerPanel.add(usernameInput, "cell 0 1");
		centerPanel.add(passwordInput, "cell 0 2");
		centerPanel.add(passwordInputConfirm, "cell 0 3");
		centerPanel.add(buttonSeperator, "cell 0 4");
		centerPanel.add(signUpButton, "cell 0 5");
		centerPanel.add(backToLoginButton, "cell 0 6");
		contentPane.add(centerPanel, "cell 0 0, gapy 20%!");

		this.setJMenuBar(menuBar.generate());

		setupFrame(); // Setup the frames properties
		addButtonListeners();
		setClientProperties(); // Setup the client theming properties for FlatLaf
		setToolTips(); // Setup the tooltips for all component objects created
	}

	/** Setups the frame i.e. Size, Re-sizable, Location */
	private void setupFrame() {
		this.pack();
		this.setLocation(1000, 300);
		this.setMinimumSize(new Dimension(frameWidth, frameHeight));
		this.setSize(new Dimension(frameWidth, frameHeight));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane.requestFocusInWindow();
	}

	/**
	 * Configure custom client properties for theming (FLatLaf). These are unique values and so are
	 * not added to properties files
	 */
	private void setClientProperties() {
		signUpText.putClientProperty("FlatLaf.style",
				"foreground: @accentColor;" + "font: $extralarge.font");
		usernameInput.putClientProperty("JTextField.placeholderText", "Enter username...");
		passwordInput.putClientProperty("JTextField.placeholderText", "Enter password...");
		passwordInputConfirm.putClientProperty("JTextField.placeholderText", "Confirm password...");
	}

	/**
	 * Configure and set the tool tips for components in the content pane
	 */
	private void setToolTips() {
		usernameInput.setToolTipText("Username");
		passwordInput.setToolTipText("Password");
		passwordInputConfirm.setToolTipText("Confirm password");
	}

	private void addButtonListeners() {
		usernameInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {}
		});

		backToLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unused")
				Login login = new Login();
				removeAll();
				dispose();
			}
		});
	}
}
