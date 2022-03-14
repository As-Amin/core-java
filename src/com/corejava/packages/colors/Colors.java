package com.corejava.packages.colors;

import java.awt.Color;

public enum Colors {
  THEME(163, 190, 140), // Same as FlatDarkLaf.properties '@themeColor'
  THEME_SECONDARY(235, 203, 139), // For titles and subheadings
  WHITE(220, 220, 220); // For text

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
