package com.corejava.packages.colors;

import java.awt.Color;

public enum DarkModeColors {
  DARK1_BACKGROUND(18, 18, 18), // Same as FlatDarkLaf.properties '@background'
  DARK2_BACKGROUND_SECONDARY(30, 30, 30), // Same as FlatDarkLaf.properties '@backgroundSecondary'
  DARK3_THEME_COLOR(163, 165, 235); // Same as FlatDarkLaf.properties '@themeColor'

  private final int r;
  private final int g;
  private final int b;
  private final String rgb;

  private DarkModeColors(final int r, final int g, final int b) {
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
