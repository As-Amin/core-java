package com.corejava.packages.colors;

import java.awt.Color;

public enum Colors {
  THEME(163, 165, 235), // Same as FlatDarkLaf.properties '@themeColor'
  WHITE(255, 255, 255);

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
