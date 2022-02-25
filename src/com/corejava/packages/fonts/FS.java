package com.corejava.packages.fonts;

public enum FS {

  // Top menu bar
  MENU_BAR(14),

  // Left side panel
  SIDE_LOGO(33),
  SIDE_TEXT(18),
  SIDE_INPUT(20),

  // Working area panel
  NOTE_TITLE(35),
  NOTE_TEXT(18),

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
