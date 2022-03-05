package com.corejava.packages.fonts;

public enum FN {
  LOGO("Consolas"), MAIN("Noto Sans JP"); // For all subtitles, menus
  // Noto Sans JP

  private final String fontName;

  private FN(final String fontName) {
    this.fontName = fontName;
  }

  public String getFN() {
    return fontName;
  }
}
