package com.corejava.packages.colors;

import java.awt.Color;

public enum Colors {
  DARK1_BACKGROUND(0, 0, 0), // Same as FlatDarkLaf.properties '@background'
  DARK2_BACKGROUND_SECONDARY(12, 12, 12), // Same as FlatDarkLaf.properties '@backgroundSecondary'
  DARK3_THEME_COLOR(163, 165, 235), // Same as FlatDarkLaf.properties '@themeColor'
  DARK4_PINK_COLOR(235, 162, 233), // For titles and subheadings
  DARK5_FADED_WHITE_COLOR(136, 139, 144), // For buttons not in focus
  DARK6_WHITE_COLOR(255, 255, 255), // For text
  DARK7_YELLOW_COLOR(235, 203, 139); // For captions and questions

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
