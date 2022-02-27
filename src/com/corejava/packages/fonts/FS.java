package com.corejava.packages.fonts;

public enum FS {

  // Top menu bar
  MENU_BAR(14),

  // Left side panel
  SIDE_LOGO(20),
  SIDE_TEXT(14),

  // Learning area panel
  TOPIC_TITLE(28),
  TOPIC_TEXT(18),

  // Login screen
  LOGIN_LOGO(37),
  LOGIN_TEXT(15),
  LOGIN_INPUT(20);

  private final int fontSize;

  private FS(final int fontSize) {
    this.fontSize = fontSize;
  }

  public int getFS() {
    return fontSize;
  }
}
