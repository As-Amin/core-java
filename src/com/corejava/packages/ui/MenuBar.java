package com.corejava.packages.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.corejava.packages.Main;

public class MenuBar extends JMenuBar {
    private LinkedHashMap<String, JMenu> AllJMenu = new LinkedHashMap<String, JMenu>();
    private LinkedHashMap<String, JMenuItem> AllJMenuItems = new LinkedHashMap<String, JMenuItem>();
    private Boolean isLoggedIn = false;

    public MenuBar(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public JMenuBar generate() {
        AllJMenu.put(Main.APP_NAME, new JMenu(Main.APP_NAME));
        if (isLoggedIn) {
            AllJMenu.put("Profile", new JMenu("Profile"));
        }
        AllJMenu.put("Help", new JMenu("Help"));

        for (JMenu menu : AllJMenu.values()) {
            this.add(menu);
        }

        // Disable app title button - places the app name first in menu
        AllJMenu.get(Main.APP_NAME).setEnabled(false);

        if (isLoggedIn) {
            AllJMenuItems.put("Profile Progress", new JMenuItem("Progress"));
            AllJMenuItems.put("Profile SignOut", new JMenuItem("Sign out"));
        }
        AllJMenuItems.put("Help About", new JMenuItem("About"));

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
        return this;
    }

    private void loadAllConfigs() {
        if (isLoggedIn) {
            AllJMenuItems.get("Profile Progress").addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    //
                }
            });
            AllJMenuItems.get("Profile SignOut").addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    //
                }
            });
        }
        AllJMenuItems.get("Help About").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                JOptionPane.showMessageDialog(null,
                        Main.APP_NAME + ": " + Main.APP_SLOGAN + "\n\n" + "Version: "
                                + Main.APP_VERSION + "\n\n" + "GitHub: "
                                + Main.APP_CONFIG_OBJECT.getProperty("app.github") + "\n\n",
                        "About", JOptionPane.INFORMATION_MESSAGE);
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
}
