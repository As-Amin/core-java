package com.corejava.packages.colors;

import java.awt.Color;

public enum Colors {
  BACKGROUND(0, 0, 0), // Same as FlatDarkLaf.properties '@background'
  BACKGROUND_SECONDARY(12, 12, 12), // Same as FlatDarkLaf.properties '@backgroundSecondary'
  THEME(235, 203, 139), // Same as FlatDarkLaf.properties '@themeColor'
  THEME_SECONDARY(234, 167, 138), // For titles and subheadings
  THEME_THIRD(173, 234, 138), // For questions, captions
  WHITE(255, 255, 255); // For text

  private final int r;
  private final int g;
  private final int b;

  private Colors(final int r, final int g, final int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public Color getColor() {
    return new Color(r, g, b);
  }
}
