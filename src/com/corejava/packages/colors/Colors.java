package com.corejava.packages.colors;

import java.awt.Color;

public enum Colors {
  BACKGROUND(0, 0, 0), // Same as FlatDarkLaf.properties '@background'
  BACKGROUND_SECONDARY(12, 12, 12), // Same as FlatDarkLaf.properties '@backgroundSecondary'
  THEME(163, 165, 235), // Same as FlatDarkLaf.properties '@themeColor'
  WHITE(255, 255, 255), // For text
  FADED_WHITE(136, 139, 144), // For buttons not in focus
  PINK(235, 162, 233), // For titles and subheadings
  YELLOW(235, 203, 139); // For captions and questions

  private final int r;
  private final int g;
  private final int b;
  private final String rgb;

  private Colors(final int r, final int g, final int b) {
    this.r = r;
    this.g = g;
    this.b = b;
    this.rgb = r + ", " + g + ", " + b;
  }

  public String getRGB() {
    return rgb;
  }

  public Color getColor() {
    return new Color(r, g, b);
  }
}
