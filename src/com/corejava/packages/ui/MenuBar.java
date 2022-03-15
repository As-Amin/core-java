package com.corejava.packages.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.corejava.packages.Main;
import org.apache.commons.configuration.ConfigurationException;

public class MenuBar {
    private LinkedHashMap<String, JMenu> AllJMenu = new LinkedHashMap<String, JMenu>();
    private LinkedHashMap<String, JMenuItem> AllJMenuItems = new LinkedHashMap<String, JMenuItem>();

    private JFrame frame;
    private JMenuBar menuBar = new JMenuBar();

    public MenuBar(JFrame frame) {
        this.frame = frame;
    }

    public void Generate() {
        AllJMenu.put(Main.APP_NAME, new JMenu(Main.APP_NAME));
        AllJMenu.put("Profile", new JMenu("Profile"));
        AllJMenu.put("Help", new JMenu("Help"));

        for (JMenu menu : AllJMenu.values()) {
            menuBar.add(menu);
        }

        // Disable app title button - places the app name first in menu
        AllJMenu.get(Main.APP_NAME).setEnabled(false);

        AllJMenuItems.put("Profile Progress", new JMenuItem("Progress"));
        AllJMenuItems.put("Profile SignOut", new JMenuItem("Sign out"));

        for (String menuKey : AllJMenu.keySet()) {
            for (String menuItemKey : AllJMenuItems.keySet()) {
                String arr[] = menuItemKey.split(" ", 2);
                String firstWord = arr[0];
                if (firstWord.equalsIgnoreCase(menuKey)) {
                    AllJMenu.get(menuKey).add(AllJMenuItems.get(menuItemKey));
                }
            }
        }

        loadAllConfigs();
        frame.setJMenuBar(menuBar);
    }

    private void loadAllConfigs() {
        profileProgressConfig();
        profileSignOutConfig();
    }

    private void profileProgressConfig() {
        AllJMenuItems.get("Profile Progress").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //
            }
        });
    }

    private void profileSignOutConfig() {
        AllJMenuItems.get("Profile SignOut").addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                //
            }
        });
    }

    /**
     * @return LinkedHashMap<String, JMenu> return the AllJMenu
     */
    public LinkedHashMap<String, JMenu> getAllJMenu() {
        return AllJMenu;
    }

    /**
     * @param AllJMenu the AllJMenu to set
     */
    public void setAllJMenu(LinkedHashMap<String, JMenu> AllJMenu) {
        this.AllJMenu = AllJMenu;
    }

    /**
     * @return LinkedHashMap<String, JMenuItem> return the AllJMenuItems
     */
    public LinkedHashMap<String, JMenuItem> getAllJMenuItems() {
        return AllJMenuItems;
    }

    /**
     * @param AllJMenuItems the AllJMenuItems to set
     */
    public void setAllJMenuItems(LinkedHashMap<String, JMenuItem> AllJMenuItems) {
        this.AllJMenuItems = AllJMenuItems;
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
     * @return JMenuBar return the menuBar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * @param menuBar the menuBar to set
     */
    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

}

